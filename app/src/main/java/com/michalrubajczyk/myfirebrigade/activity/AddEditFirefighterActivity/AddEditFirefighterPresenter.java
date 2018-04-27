package com.michalrubajczyk.myfirebrigade.activity.AddEditFirefighterActivity;

import android.util.Log;

import com.google.gson.Gson;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FirefighterRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.TrainingRequest;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.TrainingRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;
import com.michalrubajczyk.myfirebrigade.model.dto.FirefighterTraining;
import com.michalrubajczyk.myfirebrigade.model.dto.Training;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

            private List<Training> createTrainingsFromResponse(String data) {
                Gson gson = new Gson();
                Training[] training = gson.fromJson(data, Training[].class);
                return Arrays.asList(training);
            }

            @Override
            public void onError(int code) {
                Log.d("AddEdit Ffighter PR", "Error: " + code);
            }
        });
        return null;
    }

    private boolean isNewFirefighter() {
        return mFirefighterId == null;
    }

    @Override
    public void saveFirefighter(String name, String lastName, Date birthday, Date expiryMedicalTest, List<Training> trainings) {
        if (isNewFirefighter()) {
            createFirefighter(name, lastName, birthday, expiryMedicalTest, trainings);
        } else {
            updateFirefighter(name, lastName, birthday, expiryMedicalTest, trainings);
        }
    }

    private void createFirefighter(String name, String lastName, Date birthday, Date expiryMedicalTest, List<Training> trainings) {
    }

    private void updateFirefighter(String name, String lastName, Date birthday, Date expiryMedicalTest, List<Training> trainings) {

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
