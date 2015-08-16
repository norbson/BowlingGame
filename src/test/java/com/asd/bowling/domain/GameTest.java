package com.asd.bowling.domain;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.assertj.core.api.StrictAssertions.assertThat;

public class GameTest {

    Game game;

    @Before
    public void setup() {
        //given
        game = new Game();
    }

    @Ignore
    @Test
    public void oneThrow() {
        //when
        game.add(5);

        //then
        assertThat(game.score()).isEqualTo(5); // we try to get score for incomplete frame, should we allow for it?
        assertThat(game.getCurrentFrame()).isEqualTo(1);
    }

    @Test
    public void twoThrowsNoMark() {
        //when
        game.add(5);
        game.add(4);

        //then
        assertThat(game.score()).isEqualTo(9);
        assertThat(game.getCurrentFrame()).isEqualTo(2);
    }

    @Test
    public void fourThrowsNoMark() {
        //when
        game.add(5);
        game.add(4);
        game.add(7);
        game.add(2);

        //then
        assertThat(game.score()).isEqualTo(18);
        assertThat(game.scoreForFrame(1)).isEqualTo(9);
        assertThat(game.scoreForFrame(2)).isEqualTo(18);
        assertThat(game.getCurrentFrame()).isEqualTo(3);
    }

    @Test
    public void simpleSpare() {
        //when
        game.add(3);
        game.add(7);
        game.add(3);

        //then
        assertThat(game.scoreForFrame(1)).isEqualTo(13);
        assertThat(game.getCurrentFrame()).isEqualTo(2);
    }

    @Test
    public void simpleFrameAfterSpare() {
        //when
        game.add(3);
        game.add(7);
        game.add(3);
        game.add(2);

        //then
        assertThat(game.scoreForFrame(1)).isEqualTo(13);
        assertThat(game.scoreForFrame(2)).isEqualTo(18);
        assertThat(game.score()).isEqualTo(18);
    }
}
