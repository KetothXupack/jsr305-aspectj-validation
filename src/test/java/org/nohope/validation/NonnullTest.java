package org.nohope.validation;

import org.junit.Test;

import javax.annotation.Nonnull;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author <a href="mailto:ketoth.xupack@gmail.com">ketoth xupack</a>
 * @since 8/17/12 5:14 AM
 */
@SuppressWarnings("ALL")
public class NonnullTest {
    @Test
    public void callTest() {
        test("");

        try {
            test(null);
            doFail();
        } catch (final IllegalStateException e) {
            assertEquals(e.getMessage(),
                    "Argument 1 for @Nonnull parameter of class "
                    + "org.nohope.validation.NonnullTest.test(java.lang.String) must"
                    + " not be null");
        }
    }

    @Test
    public void callTest2() {
        test2(1, "");
        test2("", 1);
        test2(null, "");
        test2("", null);

        try {
            test2(1, null);
            doFail();
        } catch (final IllegalStateException e) {
            assertEquals(e.getMessage(),
                    "Argument 2 for @Nonnull parameter of class "
                    + "org.nohope.validation.NonnullTest.test2(java.lang.Integer, java.lang.String) must"
                    + " not be null");
        }

        try {
            test2(null, 1);
            doFail();
        } catch (final IllegalStateException e) {
            assertEquals(e.getMessage(),
                    "Argument 1 for @Nonnull parameter of class "
                    + "org.nohope.validation.NonnullTest.test2(java.lang.String, java.lang.Integer) must"
                    + " not be null");
        }
    }

    @Test
    public void callTest3() {
        try {
            test("", null, "");
            doFail();
        } catch (final IllegalStateException e) {
            assertEquals(e.getMessage(),
                    "Argument 2 for @Nonnull parameter of class "
                    + "org.nohope.validation.NonnullTest.test(java.lang.String, java.lang.String, java.lang.String) must"
                    + " not be null");
        }
    }

    @Test
    public void returnValueCheck() {
        try {
            nullObject();
            doFail();
        } catch (Exception e) {
            assertEquals(e.getMessage(),
                    "@Nonnull method class "
                    + "org.nohope.validation.NonnullTest.nullObject() "
                    + "must not return null");
        }

        try {
            staticNullObject();
            doFail();
        } catch (Exception e) {
            assertEquals(e.getMessage(),
                    "@Nonnull method class "
                    + "org.nohope.validation.NonnullTest.staticNullObject() "
                    + "must not return null");
        }
    }

    @Test
    public void inheritance() {
        try {
            // annotations are not inherited anyway
            new NonnullInheritance().getName();
        } catch (Exception e) {
            fail("wow! great news :)");
        }
    }

    public void test(@Nonnull final String test) {
    }

    public void test2(final Integer a, @Nonnull final String test) {
    }

    public void test2(@Nonnull final String a, final Integer test) {
    }

    public void test(final String a, @Nonnull final String test, final String b) {
    }

    public void test2(final String a, @Nonnull final String test, final String b) {
    }

    @SuppressWarnings("MethodMayBeStatic")
    @Nonnull Object nullObject() {
        return null;
    }

    @Nonnull static Object staticNullObject() {
        return null;
    }

    private static void doFail() {
        fail("NotNullAspect failed... not compiled with iajc?");
    }

    public interface TestInterfaceWithNonnull {
        @Nonnull String getName();
    }


    public class NonnullInheritance implements TestInterfaceWithNonnull {
        @Override
        public String getName() {
            return null;
        }
    }

}