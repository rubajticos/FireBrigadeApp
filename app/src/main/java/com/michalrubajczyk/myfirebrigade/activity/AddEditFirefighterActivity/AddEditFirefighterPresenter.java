package com.michalrubajczyk.myfirebrigade.activity.AddEditFirefighterActivity;

import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.FirefighterRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.Firefighter;
import com.michalrubajczyk.myfirebrigade.model.dto.Training;
import com.michalrubajczyk.myfirebrigade.utils.FireBrigadeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddEditFirefighterPresenter implements AddEditFirefighterContract.Presenter {

    private final FirefighterRequestImpl mFirefighterRequest;

    private final AddEditFirefighterContract.View mAddEditFirefighterView;

    private final String mFirefighterId;

    private boolean mIsDataMissing;

    private FireBrigadeUtils mFirebrigadeUtils;

    private SimpleDateFormat simpleDateFormat;

    public AddEditFirefighterPresenter(FirefighterRequestImpl mFirefighterRequest,
                                       AddEditFirefighterContract.View mAddEditFirefighterView,
                                       String mFirefighterId,
                                       boolean mIsDataMissing,
                                       FireBrigadeUtils mFirebrigadeUtils) {
        this.mFirefighterRequest = mFirefighterRequest;
        this.mAddEditFirefighterView = mAddEditFirefighterView;
        this.mFirefighterId = mFirefighterId;
        this.mIsDataMissing = mIsDataMissing;
        this.mFirebrigadeUtils = mFirebrigadeUtils;

        mAddEditFirefighterView.setPresenter(this);

        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    }

    @Override
    public void start() {
        if (!isNewFirefighter() && mIsDataMissing) {
            populateFirefighter();
        }

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
        mFirefighterRequest.getFirefighterById(firefighterId, new DataListener() {
            @Override
            public void onSuccess(String data) {
                Firefighter firefighter = new Firefighter(data);
                if (mAddEditFirefighterView.isActive()) {
                    mAddEditFirefighterView.setName(firefighter.getName());
                    mAddEditFirefighterView.setLastName(firefighter.getLastName());
                    mAddEditFirefighterView.setBirthday(simpleDateFormat.format(firefighter.getBirthday()));
                    mAddEditFirefighterView.setExpiryMedicalTests(simpleDateFormat.format(firefighter.getExpiryMedicalTest()));
                }
                mIsDataMissing = false;
            }

            @Override
            public void onError(int code) {
                if (mAddEditFirefighterView.isActive()) {
                    mAddEditFirefighterView.showInwalidFirefighterError();
                }
            }
        });

    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }


}
