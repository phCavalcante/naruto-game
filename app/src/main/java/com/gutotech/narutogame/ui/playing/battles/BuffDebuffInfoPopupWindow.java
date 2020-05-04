package com.gutotech.narutogame.ui.playing.battles;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.databinding.DataBindingUtil;

import com.gutotech.narutogame.R;
import com.gutotech.narutogame.data.model.Classe;
import com.gutotech.narutogame.data.model.Jutsu;
import com.gutotech.narutogame.data.model.JutsuInfo;
import com.gutotech.narutogame.databinding.PopupBuffDebuffInfoBinding;
import com.gutotech.narutogame.utils.SoundUtil;

public class BuffDebuffInfoPopupWindow extends PopupWindow {
    private Context mContext;
    private PopupBuffDebuffInfoBinding mBinding;

    public BuffDebuffInfoPopupWindow(Context context) {
        super(context);
        mContext = context;

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.popup_buff_debuff_info, null, false);

        setContentView(mBinding.getRoot());

        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOutsideTouchable(true);
    }

    public void setBuffOrDebuff(Jutsu jutsu) {
        JutsuInfo jutsuInfo = jutsu.getJutsuInfo();

        mBinding.setJutsu(jutsu);
        mBinding.setJutsuInfo(jutsuInfo);

        if (jutsuInfo.type == Jutsu.Type.BUFF) {
            mBinding.effectsTextView.setText(R.string.effects_on_your_character);

            if (jutsu.getClasse() == Classe.NIN || jutsu.getClasse() == Classe.GEN) {
                mBinding.atkTaiBukTextView.setVisibility(View.GONE);
            } else {
                mBinding.atkNinGenTextView.setVisibility(View.GONE);
            }
        } else {
            mBinding.effectsTextView.setText(R.string.effects_on_your_enemy);
        }

        if (jutsu.getBaseDefense() == 0) {
            mBinding.defTextView.setVisibility(View.GONE);
        }

        if (jutsu.getAtk() == 0) {
            mBinding.atkNinGenTextView.setVisibility(View.GONE);
            mBinding.atkTaiBukTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
        SoundUtil.play(mContext, R.raw.sound_pop);
    }
}
