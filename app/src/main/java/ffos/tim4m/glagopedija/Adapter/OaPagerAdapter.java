package ffos.tim4m.glagopedija.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import ffos.tim4m.glagopedija.FragmentOaCilj;
import ffos.tim4m.glagopedija.FragmentOaProjekt;
import ffos.tim4m.glagopedija.FragmentOaSadrzaj;

public class OaPagerAdapter extends FragmentStateAdapter {

    public OaPagerAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new FragmentOaProjekt();
            case 1:
                return new FragmentOaCilj();
            default:
                return new FragmentOaSadrzaj();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
