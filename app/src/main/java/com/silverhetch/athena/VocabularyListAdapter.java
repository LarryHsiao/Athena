package com.silverhetch.athena;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silverhetch.athena.databinding.ItemVocabularyBinding;
import com.silverhetch.athena.vocabulary.Vocabulary;
import com.silverhetch.util.view.DataBindingViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mikes on 12/15/2017.
 */

public class VocabularyListAdapter extends RecyclerView.Adapter<DataBindingViewHolder> {
    private final List<Vocabulary> data;

    public VocabularyListAdapter(Vocabulary[] vocabularies) {
        this.data = new ArrayList<>(Arrays.asList(vocabularies));
    }

    @Override
    public DataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemVocabularyBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_vocabulary, parent, false);
        return new DataBindingViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(DataBindingViewHolder holder, int position) {
        final Vocabulary vocabulary = data.get(position);
        ItemVocabularyBinding binding = holder.getViewDataBinding();
        binding.setVocabulary(vocabulary);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://translate.google.com")));

            }
        });
    }

    public void add(Vocabulary vocabulary) {
        data.add(0, vocabulary);
        notifyItemInserted(0);
    }

    public void remove(Vocabulary vocabulary) {
        final int position = data.indexOf(vocabulary);
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
