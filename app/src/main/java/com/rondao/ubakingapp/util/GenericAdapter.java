package com.rondao.ubakingapp.util;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rondao.ubakingapp.BR;

import java.util.List;

public class GenericAdapter<T> extends RecyclerView.Adapter<GenericAdapter.GenericViewHolder> {
    private final ListItemClickListener<T> mOnClickListener;

    private List<T> mDataSet;
    private int mLayoutId;

    public GenericAdapter(int layoutId) {
        this(layoutId, null);
    }

    public GenericAdapter(int layoutId, ListItemClickListener<T> onClickListener) {
        mLayoutId = layoutId;
        mOnClickListener = onClickListener;
    }

    public interface ListItemClickListener<T> {
        void onListItemClick(T obj);
    }

    public class GenericViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding mBinding;

        public GenericViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

            if (mOnClickListener != null) {
                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnClickListener.onListItemClick(mDataSet.get(getAdapterPosition()));
                    }
                });
            }
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
