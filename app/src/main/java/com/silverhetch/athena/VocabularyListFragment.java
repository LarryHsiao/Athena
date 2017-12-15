package com.silverhetch.athena;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silverhetch.athena.databinding.FragmentVocabularyBinding;
import com.silverhetch.athena.databinding.ItemVocabularyBinding;
import com.silverhetch.athena.vocabulary.Vocabularies;
import com.silverhetch.athena.vocabulary.VocabulariesFactory;
import com.silverhetch.util.view.DataBindingViewHolder;

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
        ItemTouchHelper.Callback itemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                ItemVocabularyBinding binding = ((DataBindingViewHolder) viewHolder).getViewDataBinding();
                binding.getVocabulary().delete();
            }
        };
        new ItemTouchHelper(itemTouchCallback).attachToRecyclerView(list);
    }
}
