package com.michalrubajczyk.myfirebrigade.activity.AnalysisActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.michalrubajczyk.myfirebrigade.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class AnalysisFragment extends Fragment implements AnalysisContract.View {
    private static final String TAG = "Analysis Fragment";
    @BindView(R.id.analysis_progress)
    ProgressBar mAnalysisProgress;
    @BindView(R.id.detail_analysis_radio)
    RadioButton mDetailAnalysisRadio;
    @BindView(R.id.general_analysis_radio2)
    RadioButton mGeneralAnalysisRadio2;
    @BindView(R.id.anylysis_method_group)
    RadioGroup mAnylysisMethodGroup;
    @BindView(R.id.full_report_radio)
    RadioButton mFullReportRadio;
    @BindView(R.id.simple_report_radio)
    RadioButton mSimpleReportRadio;
    @BindView(R.id.report_type)
    RadioGroup mReportType;
    @BindView(R.id.analysis_confirm_button)
    Button mAnalysisConfirmButton;
    Unbinder unbinder;

    private AnalysisContract.Presenter mPresenter;


    public AnalysisFragment() {
    }

    public static AnalysisFragment newInstance() {
        return new AnalysisFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        hideLoadingIndicator();
    }

    @Override
    public void setPresenter(AnalysisContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.analysis_frag, container, false);

        setHasOptionsMenu(true);

        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mAnalysisProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        mAnalysisProgress.setVisibility(View.GONE);
    }

    @Override
    public void showReport() {
        // TODO: 28/06/2018 pokazywanie raportu PDF
    }

    @Override
    public void showNoAnalysisTypeError() {
        showMessage(getString(R.string.analysis_type_error));
    }

    @Override
    public void showNoReportTypeError() {
        showMessage(getString(R.string.report_choose_error));
    }

    @Override
    public void showAnalysisError() {
        showMessage(getString(R.string.analysis_error));
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
