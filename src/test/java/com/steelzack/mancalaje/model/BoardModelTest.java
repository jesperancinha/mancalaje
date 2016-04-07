package com.steelzack.mancalaje.model;

import com.steelzack.mancalaje.objects.Pit;
import com.steelzack.mancalaje.objects.PitImpl;
import com.steelzack.mancalaje.objects.Player;
import com.steelzack.mancalaje.objects.PlayerImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by joaofilipesabinoesperancinha on 07-04-16.
 */
public class BoardModelTest {
    @Test
    public void getclickMode() throws Exception {
        final BoardModel model = new BoardModel();
        Pit pit = new PitImpl() {
            @Override
            public Player getPlayer() {
                return new PlayerImpl(1);
            }
        };

        assertEquals(1, model.getclickMode(pit, 1, 1));
    }

}