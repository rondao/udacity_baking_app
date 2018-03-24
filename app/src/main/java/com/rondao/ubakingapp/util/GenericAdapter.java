package com.rondao.ubakingapp.util;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rondao.ubakingapp.BR;
import com.rondao.ubakingapp.data.model.Recipe;

import java.util.List;

public class GenericAdapter<T> extends RecyclerView.Adapter<GenericAdapter.GenericViewHolder> {
    private List<T> mDataSet;
    private int mLayoutId;

    public GenericAdapter(int layoutId) {
        mLayoutId = layoutId;
    }

    public class GenericViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding mBinding;

        public GenericViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(Object obj) {
            mBinding.setVariable(BR.obj, obj);
            mBinding.executePendingBindings();
        }
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                inflater, mLayoutId, parent, false);
        return new GenericViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(GenericAdapter.GenericViewHolder holder, int position) {
        holder.bind(mDataSet.get(position));
    }

    public void setDataSet(List<T> dataSet) {
        mDataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataSet == null ? 0 : mDataSet.size();
    }
}
