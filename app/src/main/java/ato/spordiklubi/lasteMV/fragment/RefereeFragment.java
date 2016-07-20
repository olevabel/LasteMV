package ato.spordiklubi.lasteMV.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ato.spordiklubi.lasteMV.R;
import ato.spordiklubi.lasteMV.activity.DiciplineActivity;
import ato.spordiklubi.lasteMV.view.TypefaceEditText;
import ato.spordiklubi.lasteMV.view.TypefaceTextView;


/**
 * Created by Olev on 18/08/2015.
 */
public class RefereeFragment extends BaseFragment {


    private String COLUMN_REFEREE = "kohtunik";
    private int selectedAge;

    private View viewRoot;
    private TypefaceEditText inputRefereeName;
    private TypefaceTextView btnEnter;
    private TypefaceTextView btnBack;

    private static final String ARG_SELECTED_AGE = RefereeFragment.class.getName() + ".ARG_SELECTED_AGE";

    public static RefereeFragment newInstance(int selectedAge) {
        final RefereeFragment refereeFragment = new RefereeFragment();
        refereeFragment.setArguments(createArguments(selectedAge));
        return refereeFragment;
    }

    protected static Bundle createArguments(final int selectedAge) {
        final Bundle args = new Bundle();
        args.putInt(ARG_SELECTED_AGE, selectedAge);
        return args;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        selectedAge = getArguments().getInt(ARG_SELECTED_AGE);
        viewRoot = inflater.inflate(R.layout.fragment_referee, null);
        inputRefereeName = (TypefaceEditText) viewRoot.findViewById(R.id.input_referee_name);
        btnEnter = (TypefaceTextView) viewRoot.findViewById(R.id.btn_enter);
        btnBack = (TypefaceTextView) viewRoot.findViewById(R.id.btn_back);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getActivity(), DiciplineActivity.class);
                intent.putExtra(DiciplineActivity.EXTRA_SELECTED_AGE, selectedAge);
                intent.putExtra(DiciplineActivity.EXTRA_REFEREE_NAME,inputRefereeName.getText().toString());
                startActivity(intent);


            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return viewRoot;
    }
}


