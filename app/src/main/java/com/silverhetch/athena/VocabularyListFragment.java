package com.silverhetch.athena;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silverhetch.athena.databinding.FragmentVocabularyBinding;
import com.silverhetch.athena.vocabulary.Vocabularies;
import com.silverhetch.athena.vocabulary.VocabulariesFactory;

/**
 * Created by mikes on 12/15/2017.
 */

public class VocabularyListFragment extends Fragment {
    private FragmentVocabularyBinding binding;

    public static Fragment newInstance() {
        return new VocabularyListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vocabulary, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Vocabularies vocabularies = new VocabulariesFactory(getContext()).vocabularies();
        RecyclerView list = binding.getRoot().findViewById(R.id.vocabularyList_recyclerView);
        list.setAdapter(new VocabularyListAdapter(vocabularies.all()));
        vocabularies.add("123","123");
    }
}
