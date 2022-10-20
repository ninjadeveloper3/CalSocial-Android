package com.CalSocial.ui.events.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.TimeSuggestionsResponse;
import com.CalSocial.ui.base.BaseViewHolder;
import com.CalSocial.ui.events.selectTimeSuggestions.SelectTimeSuggestionsEventMvpView;
import com.CalSocial.ui.events.selectTimeSuggestions.SelectTimeSuggestionsEventPresenter;

import java.util.List;
import java.util.Random;

public class TimeSuggestionsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE = 0;
    private Callback mCallback;
    private List<TimeSuggestionsResponse.Suggestion> mSuggestionsResponseList;
    private Context context;
    private Random rand;
    private SelectTimeSuggestionsEventPresenter<SelectTimeSuggestionsEventMvpView> presenter;

    public TimeSuggestionsAdapter(List<TimeSuggestionsResponse.Suggestion> suggestionList, Context context) {
        mSuggestionsResponseList = suggestionList;
        this.context = context;
        rand = new Random();
    }

    public void setCallback(TimeSuggestionsAdapter.Callback callback) {
        mCallback = callback;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new TimeSuggestionsViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.events_item_select_time_suggested_layout, parent, false), mSuggestionsResponseList, context, presenter);

    }


    @Override
    public int getItemViewType(int position) {
        if (mSuggestionsResponseList != null && mSuggestionsResponseList.size() > 0) {
            return VIEW_TYPE;
        }

        return VIEW_TYPE;

    }

    @Override
    public int getItemCount() {
        if (mSuggestionsResponseList != null && mSuggestionsResponseList.size() > 0) {
            return mSuggestionsResponseList.size();
        } else {
            return 1;
        }
    }


    public void addItems(List<TimeSuggestionsResponse.Suggestion> suggestionList) {
        mSuggestionsResponseList.addAll(suggestionList);
        notifyDataSetChanged();
    }


    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }

    public void setPresenter(SelectTimeSuggestionsEventPresenter<SelectTimeSuggestionsEventMvpView> presenter) {
        this.presenter = presenter;
    }

}
