package com.rondao.ubakingapp.util;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rondao.ubakingapp.BR;
import com.rondao.ubakingapp.databinding.RecipeCardBinding;

public abstract class GenericAdapter extends RecyclerView.Adapter<GenericAdapter.GenericViewHolder> {
    public class GenericViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding  mBiding;

        public GenericViewHolder(ViewDataBinding  binding) {
            super(binding.getRoot());
            mBiding = binding;
        }

        public void bind(Object obj) {
            mBiding.setVariable(BR.obj, obj);
            mBiding.executePendingBindings();
        }
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                inflater, viewType, parent, false);
        return new GenericViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(GenericAdapter.GenericViewHolder holder, int position) {
        holder.bind(getObjForPosition(position));
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    protected abstract Object getObjForPosition(int position);

    protected abstract int getLayoutIdForPosition(int position);
}