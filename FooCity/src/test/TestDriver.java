package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.tile.*;
import test.grid.*;
/**
 * <p>
 * This class is a placeholder for the the JUnit test suite annotations.
 * </p>
 *
 * <p>
 * More details at http://radio.javaranch.com/lasse/2006/07/27/1154024535662.html
 * </p>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestTileDelegation.class,
        TestTileGetSubTile.class,
        TestGridInstantiation.class,
})
public class TestDriver {}
