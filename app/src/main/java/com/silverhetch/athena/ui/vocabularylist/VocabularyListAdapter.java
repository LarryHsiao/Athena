package com.silverhetch.athena.ui.vocabularylist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.silverhetch.athena.R;
import com.silverhetch.athena.databinding.ItemVocabularyBinding;
import com.silverhetch.athena.vocabulary.Vocabulary;
import com.silverhetch.util.view.DataBindingViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mikes on 12/15/2017.
 */
class VocabularyListAdapter extends RecyclerView.Adapter<DataBindingViewHolder> {
    private final List<Vocabulary> data;
    private final LoaderManager loaderManager;

    VocabularyListAdapter(LoaderManager loaderManager, Vocabulary[] vocabularies) {
        this.loaderManager = loaderManager;
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
        final ItemVocabularyBinding binding = holder.getViewDataBinding();
        binding.setVocabulary(vocabulary);
        if (vocabulary.translation().isEmpty()) {
            translate(vocabulary, binding);
        }
    }

    private void translate(final Vocabulary vocabulary, final ItemVocabularyBinding binding) {
        loaderManager.initLoader(((int) vocabulary.id()), null, new LoaderManager.LoaderCallbacks<Vocabulary>() {
            @Override
            public Loader<Vocabulary> onCreateLoader(int id, Bundle args) {
                return new TranslationLoader(binding.getRoot().getContext(), vocabulary.id());
            }

            @Override
            public void onLoadFinished(Loader<Vocabulary> loader, Vocabulary translatedVocabulary) {
                if (translatedVocabulary == null || translatedVocabulary.translation().equals(translatedVocabulary.value())) {
                    return;
                }
                notifyItemChanged(translatedVocabulary);
                loaderManager.destroyLoader(loader.getId());
            }

            @Override
            public void onLoaderReset(Loader<Vocabulary> loader) {
            }
        }).forceLoad();
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

    private void notifyItemChanged(Vocabulary translatedVocabulary) {
        for (int i = 0; i < data.size(); i++) {
            Vocabulary original = data.get(i);
            if (original.id() == translatedVocabulary.id()) {
                data.add(i, translatedVocabulary);
                data.remove(original);
                notifyItemChanged(i);
            }
        }
    }
}
