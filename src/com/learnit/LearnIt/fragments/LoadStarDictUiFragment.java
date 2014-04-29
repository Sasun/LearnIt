package com.learnit.LearnIt.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.learnit.LearnIt.R;

import net.yscs.android.square_progressbar.SquareProgressBar;

public class LoadStarDictUiFragment extends Fragment {
	public final static String TAG = "ui_load_dict";

    private TextView _tvTitle;
	private TextView _tvDictInfo;
	private TextView _tvMayClose;
	ProgressBar _progressBar;
	SquareProgressBar _squareProgressBar;
	boolean dictLoaded = false;

	public boolean isDictLoaded() {
		return dictLoaded;
	}

    @Override
    public void onAttach(Activity activity) {
	    super.onAttach(activity);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dict_to_sql, container, false);
	    _progressBar = (ProgressBar) v.findViewById(R.id.progress_load_dict);
        _tvTitle = (TextView) v.findViewById(R.id.text_dict_to_sql_title);
        _tvDictInfo = (TextView) v.findViewById(R.id.text_dictionary_info);
	    _tvMayClose = (TextView) v.findViewById(R.id.text_dict_to_sql_can_close_this);
	    _tvDictInfo.setVisibility(View.INVISIBLE);
	    _tvMayClose.setVisibility(View.INVISIBLE);
        String title = null;
        String dictInfo = null;
        if (savedInstanceState!=null)
        {
            title = savedInstanceState.getString("Title");
            dictInfo = savedInstanceState.getString("DictInfo");
        }
        if (title!=null)
        {
            _tvTitle.setText(title);
            _tvDictInfo.setText(dictInfo);
        }

	    _squareProgressBar = (SquareProgressBar) v.findViewById(R.id.square_progress);
	    _squareProgressBar.setImage(R.drawable.hourglass);
	    _squareProgressBar.setColor(getString(R.color.highlight));
	    _squareProgressBar.setProgress(0);
	    _squareProgressBar.setWidth(10);
	    _squareProgressBar.setOpacity(false);

        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setRetainInstance(true);
    }

    private void setTitleText(String text)
    {
        if (_tvTitle!=null)
            _tvTitle.setText(text);
    }

    private void setDictInfoText(String text)
    {
        if (_tvDictInfo!=null) {
	        _tvDictInfo.setText(text);
        }
    }

	public void onSuccess(String dictInfoText) {
		if (isAdded()) {
			setDictInfoText(dictInfoText);
			setTitleText(this.getString(R.string.dict_sql_success));
			_tvDictInfo.setVisibility(View.VISIBLE);
			_tvMayClose.setVisibility(View.VISIBLE);
			dictLoaded = true;
		}
	}

	public void onFail() {
		if (isAdded()) {
			setTitleText(this.getString(R.string.dict_sql_no_dict));
			_squareProgressBar.setImage(R.drawable.stop);
		}
	}

	public void setProgress(Double i) {
		if (_progressBar.getVisibility() == View.VISIBLE) {
			_progressBar.setVisibility(View.INVISIBLE);
		}
		_squareProgressBar.setProgress(i);
	}

}
