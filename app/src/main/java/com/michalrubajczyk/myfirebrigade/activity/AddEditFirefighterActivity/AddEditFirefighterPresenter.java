package com.michalrubajczyk.myfirebrigade.activity.AddEditFirefighterActivity;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FirefighterRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.TrainingRequest;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.TrainingRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;
import com.michalrubajczyk.myfirebrigade.model.dto.FirefighterTraining;
import com.michalrubajczyk.myfirebrigade.model.dto.Training;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddEditFirefighterPresenter implements AddEditFirefighterContract.Presenter {
    public static final String TAG = "AdEd Firefither PRES";

    private final FirefighterRequestImpl mFirefighterRequest;

    private final TrainingRequest mTrainingRequest;

    private final AddEditFirefighterContract.View mAddEditFirefighterView;

    private final String mFirefighterId;

    private boolean mIsDataMissing;

    private FireBrigadeUtils mFirebrigadeUtils;

    private SimpleDateFormat simpleDateFormat;

    public AddEditFirefighterPresenter(FirefighterRequestImpl mFirefighterRequest,
                                       TrainingRequestImpl mTrainingRequest,
                                       AddEditFirefighterContract.View mAddEditFirefighterView,
                                       String mFirefighterId,
                                       boolean mIsDataMissing,
                                       FireBrigadeUtils mFirebrigadeUtils) {
        this.mFirefighterRequest = mFirefighterRequest;
        this.mTrainingRequest = mTrainingRequest;
        this.mAddEditFirefighterView = mAddEditFirefighterView;
        this.mFirefighterId = mFirefighterId;
        this.mIsDataMissing = mIsDataMissing;
        this.mFirebrigadeUtils = mFirebrigadeUtils;

        mAddEditFirefighterView.setPresenter(this);

        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    }

    @Override
    public void start() {
        setTrainings();
        if (!isNewFirefighter() && mIsDataMissing) {
            populateFirefighter();
        }

    }


    private List<Training> setTrainings() {
        mTrainingRequest.getAllTrainings(new DataListener() {
            @Override
            public void onSuccess(String data) {
                List<Training> trainings;
                trainings = createTrainingsFromResponse(data);
                List<String> trainingNames = getTrainingNames(trainings);
                mAddEditFirefighterView.setTrainingNames(trainingNames);
                Log.d(TAG, "Trainings: " + trainingNames.toString());
            }

            private List<String> getTrainingNames(List<Training> trainings) {
                List<String> names = new ArrayList<>();
                for (Training t : trainings) {
                    names.add(t.getName());
                }
                return names;
            }

            @Override
            public void onError(int code) {
                Log.d("AddEdit Ffighter PR", "Error: " + code);
            }
        });
        return null;
    }

    private List<Training> createTrainingsFromResponse(String data) {
        Gson gson = new Gson();
        Training[] training = gson.fromJson(data, Training[].class);
        return Arrays.asList(training);
    }

    private boolean isNewFirefighter() {
        return mFirefighterId == null;
    }

    @Override
    public void saveFirefighter(String name, String lastName, String birthday, String expiryMedicalTest, HashMap<String, String> trainings) {
        if (isNewFirefighter()) {
            Firefighter firefighter = prepareFirefighter(name, lastName, birthday, expiryMedicalTest);
            List<FirefighterTraining> trainingsList = prepareTrainings(trainings);
            createFirefighter(firefighter, trainingsList);
        } else {
            Firefighter firefighter = prepareFirefighter(name, lastName, birthday, expiryMedicalTest);
            List<FirefighterTraining> trainingsList = prepareTrainings(trainings);
            updateFirefighter(firefighter, trainingsList);
        }
    }


    private Firefighter prepareFirefighter(String name, String lastName, String birthday, String expiryMedicalTest) {
        Firefighter firefighter = new Firefighter();
        firefighter.setName(name);
        firefighter.setLastName(lastName);
        try {
            firefighter.setBirthday(simpleDateFormat.parse(birthday));
            firefighter.setExpiryMedicalTest(simpleDateFormat.parse(expiryMedicalTest));
        } catch (ParseException e) {
            Log.d(TAG, "błąd podczas parsowania daty");
        }
        return firefighter;
    }

    private List<FirefighterTraining> prepareTrainings(HashMap<String, String> trainings) {
        final List<FirefighterTraining> tr = new ArrayList<>();
        mTrainingRequest.getAllTrainings(new DataListener() {
            @Override
            public void onSuccess(String data) {
                List<Training> baseTrainings = createTrainingsFromResponse(data);
                List<FirefighterTraining> firefighterTrainings = new ArrayList<>();
                for (Map.Entry<String, String> entry : trainings.entrySet()) {
                    Training training = getTrainingByName(baseTrainings, entry.getKey());
                    Log.d(TAG, "wybrane szkolenie: " + training.toString());
                    FirefighterTraining firefighterTraining = new FirefighterTraining();
                    firefighterTraining.setTraining(training);
                    try {
                        firefighterTraining.setTrainingDate(simpleDateFormat.parse(entry.getValue()));
                    } catch (ParseException e) {
                        Log.d(TAG, "błąd podczas parsowania daty");
                    } catch (NullPointerException e) {
                        Log.d(TAG, "brak daty");
                    }
                    tr.add(firefighterTraining);
                }
                Log.d(TAG, "firefighterTrainings size " + firefighterTrainings.size());

            }

            private Training getTrainingByName(List<Training> trainings, String key) {
                for (Training training : trainings) {
                    if (training.getName().equals(key)) return training;
                }
                return null;
            }

            @Override
            public void onError(int code) {
                mAddEditFirefighterView.showServerError();
            }
        });
        return tr;
    }

    private void createFirefighter(Firefighter firefighter, List<FirefighterTraining> trainings) {
        if (firefighter.isValid()) {
            mFirefighterRequest.addFirefighterToFireBrigade(firefighter, mFirebrigadeUtils.getFireBrigadeIdFromSharedPreferences(), new DataListener() {
                @Override
                public void onSuccess(String data) {
                    Firefighter addedFirefighter = prepareFirefighterFromResponse(data);
                    Log.d(TAG, "dodany strażak: " + addedFirefighter.toString());

                    List<FirefighterTraining> trainingsCandidate = trainings;
                    for (FirefighterTraining tr : trainingsCandidate) {
                        tr.setFirefighter(addedFirefighter);
                    }
                    mFirefighterRequest.addFirefighterTrainings(trainingsCandidate, new DataListener() {
                        @Override
                        public void onSuccess(String data) {
                            Log.d(TAG, "dodane umiejetnosci!");
                            Log.d(TAG, "dodane szkolenia: " + data);
                        }

                        @Override
                        public void onError(int code) {
                            Log.d(TAG, "blad przy dodawaniu umiejetnosci!");
                            mAddEditFirefighterView.showInwalidTrainingsError();
                        }
                    });
                    mAddEditFirefighterView.showFirefighter();
                }

                @Override
                public void onError(int code) {
                    mAddEditFirefighterView.showInwalidFirefighterError();
                }
            });
        } else {
            mAddEditFirefighterView.showInwalidFirefighterError();
        }
    }

    private Firefighter prepareFirefighterFromResponse(String data) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Firefighter firefighter = gson.fromJson(data, Firefighter.class);
        return firefighter;
    }

    private void updateFirefighter(Firefighter firefighter, List<FirefighterTraining> trainingList) {
        if (isNewFirefighter()) {
            throw new RuntimeException(("updateFirefighter() was called by firefighter is new"));
        }
        int firefighterId = Integer.parseInt(mFirefighterId);
        Firefighter updateFirefighter = firefighter;
        firefighter.setIdFirefighter(firefighterId);
        mFirefighterRequest.updateFirefighter(updateFirefighter, new DataListener() {
            @Override
            public void onSuccess(String data) {
                if (trainingList.size() > 0) {
                    Firefighter updatedFirefighter = prepareFirefighterFromResponse(data);

                    Log.d(TAG, "zaktualizowany strażak: " + updatedFirefighter.toString());

                    List<FirefighterTraining> trainingsCandidate = trainingList;
                    for (FirefighterTraining tr : trainingsCandidate) {
                        tr.setFirefighter(updatedFirefighter);
                    }
                    mFirefighterRequest.updateFirefighterTrainings(trainingsCandidate, new DataListener() {
                        @Override
                        public void onSuccess(String data) {
                            mAddEditFirefighterView.showFirefighter();
                        }

                        @Override
                        public void onError(int code) {
                            mAddEditFirefighterView.showUpdateFirefighterTrainingsError();
                        }
                    });
                } else {
                    mAddEditFirefighterView.showFirefighter();
                }
            }

            @Override
            public void onError(int code) {
                mAddEditFirefighterView.showUpdateFirefighterError();
            }
        });
    }

    @Override
    public void populateFirefighter() {
        if (isNewFirefighter()) {
            throw new RuntimeException("populateFirefighter() was called but firefighter is new");
        }

        int firefighterId = Integer.parseInt(mFirefighterId);
        getAndSetMyFirefighterDetails(firefighterId);
        getAndSetMyFirefighterTrainings(firefighterId);
    }

    private void getAndSetMyFirefighterDetails(int firefighterId) {
        mFirefighterRequest.getFirefighterById(firefighterId, new DataListener() {
            @Override
            public void onSuccess(String data) {
                Log.d(TAG, "Pobralem strazaka");
                Firefighter firefighter = new Firefighter(data);
                if (mAddEditFirefighterView.isActive()) {
                    if (firefighter != null) {
                        Log.d(TAG, "Ustawiam strażaka");
                        mAddEditFirefighterView.setName(firefighter.getName());
                        mAddEditFirefighterView.setLastName(firefighter.getLastName());
                        mAddEditFirefighterView.setBirthday(simpleDateFormat.format(firefighter.getBirthday()));
                        mAddEditFirefighterView.setExpiryMedicalTests(simpleDateFormat.format(firefighter.getExpiryMedicalTest()));
                        mIsDataMissing = false;
                    } else {
                        Log.d(TAG, "Strazak NULL");
                    }
                }

            }

            @Override
            public void onError(int code) {
                Log.d(TAG, "Nie pobralem strażaka");
                if (mAddEditFirefighterView.isActive()) {
                    mAddEditFirefighterView.showInwalidFirefighterError();
                }
            }
        });
    }

    private void getAndSetMyFirefighterTrainings(int firefighterId) {
        mFirefighterRequest.getFirefighterTrainings(firefighterId, new DataListener() {
            @Override
            public void onSuccess(String data) {
                Log.d(TAG, "Pobrane treningi: " + data);
                List<FirefighterTraining> trainings = makeFirefighterTrainingsFromResponse(data);
                Log.d(TAG, "Pobrane treningi: " + trainings.toString());
                if (mAddEditFirefighterView.isActive()) {
                    if (trainings != null) {
                        Log.d(TAG, "Ustawiam szkolenia");
                        HashMap<String, String> trainingsNamesAndDates = new HashMap<>();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
                        for (FirefighterTraining ft : trainings) {
                            trainingsNamesAndDates.put(ft.getTraining().getName(), dateFormat.format(ft.getTrainingDate()));
                        }
                        mAddEditFirefighterView.setTrainings(trainingsNamesAndDates);
                        mIsDataMissing = false;
                    } else {
                        Log.d(TAG, "szkolenia NULL");
                    }
                }
            }

            private List<FirefighterTraining> makeFirefighterTrainingsFromResponse(String data) {
                Gson gson = new Gson();
                FirefighterTraining[] firefighterTrainingsArray = gson.fromJson(data, FirefighterTraining[].class);
                return Arrays.asList(firefighterTrainingsArray);
            }

            @Override
            public void onError(int code) {
                Log.d(TAG, "Nie pobralem szkolen");
                if (mAddEditFirefighterView.isActive()) {
                    mAddEditFirefighterView.showInwalidTrainingsError();
                }
            }
        });
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }


}
