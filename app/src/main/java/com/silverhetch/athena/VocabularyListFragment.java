package com.silverhetch.athena;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.silverhetch.athena.databinding.FragmentVocabularyBinding;
import com.silverhetch.athena.databinding.ItemVocabularyBinding;
import com.silverhetch.athena.vocabulary.Vocabularies;
import com.silverhetch.athena.vocabulary.VocabulariesFactory;
import com.silverhetch.athena.vocabulary.Vocabulary;
import com.silverhetch.util.view.DataBindingViewHolder;

import static android.support.v7.widget.helper.ItemTouchHelper.END;
import static android.view.KeyEvent.ACTION_DOWN;
import static android.view.KeyEvent.KEYCODE_ENTER;
import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;

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
        final VocabularyListAdapter adapter = new VocabularyListAdapter(vocabularies.all());
        final RecyclerView list = binding.getRoot().findViewById(R.id.vocabularyList_recyclerView);
        list.setHasFixedSize(true);
        list.setAdapter(adapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, END) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                ItemVocabularyBinding binding = ((DataBindingViewHolder) viewHolder).getViewDataBinding();
                Vocabulary selected = binding.getVocabulary();
                selected.delete();
                adapter.remove(selected);
            }
        }).attachToRecyclerView(list);

        final EditText insertEditText = view.findViewById(R.id.vocabularyList_insertEditText);
        insertEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (insertEditText.getText().toString().isEmpty()) {
                    return true;
                }
                if ((event != null && KEYCODE_ENTER == event.getKeyCode() && event.getAction() == ACTION_DOWN) || IME_ACTION_DONE == actionId) {
                    Vocabulary newVocabulary = vocabularies.add(v.getText().toString());
                    adapter.add(newVocabulary);
                    v.setText("");
                    return true;
                }
                return false;
            }
        });
    }
}
