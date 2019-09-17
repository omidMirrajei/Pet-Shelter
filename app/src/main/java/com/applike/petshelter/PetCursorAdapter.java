package com.applike.petshelter;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.applike.petshelter.data.PetContract.PetEntry;

public class PetCursorAdapter extends CursorAdapter {

    public PetCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView nameTextView = view.findViewById(R.id.text_name);
        TextView summaryTextView = view.findViewById(R.id.text_summary);
        ImageView genderImageView = view.findViewById(R.id.image_gender);

        int nameColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME);
        int breedColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED);
        int genderColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_GENDER);

        String petName = cursor.getString(nameColumnIndex);
        String petBreed = cursor.getString(breedColumnIndex);
        int petGender = cursor.getInt(genderColumnIndex);

        if (TextUtils.isEmpty(petBreed)) {
            petBreed = context.getString(R.string.unknown_breed);
        }

        nameTextView.setText(petName);
        summaryTextView.setText(petBreed);

        switch (petGender) {
            case PetEntry.GENDER_UNKNOWN:
                genderImageView.setImageResource(R.drawable.ic_gender_male_female);
                break;

            case PetEntry.GENDER_FEMALE:
                genderImageView.setImageResource(R.drawable.ic_gender_female);
                break;

            case PetEntry.GENDER_MALE:
                genderImageView.setImageResource(R.drawable.ic_gender_male);
                break;
        }
    }
}
