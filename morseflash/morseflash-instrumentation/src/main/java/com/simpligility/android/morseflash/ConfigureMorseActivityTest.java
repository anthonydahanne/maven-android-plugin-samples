/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.simpligility.android.morseflash;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

/**
 * Make sure that the main launcher activity opens up properly, which will be
 * verified by {@link ActivityInstrumentationTestCase#testActivityTestCaseSetUpProperly}.
 */
public class ConfigureMorseActivityTest extends ActivityInstrumentationTestCase2<ConfigureMorseActivity> {
    public boolean setup = false;
    private static final String TAG = "StartTests";
    ConfigureMorseActivity mActivity = null;
    Instrumentation mInst = null;
    private Button enterButton;
    /**
     * The first constructor parameter must refer to the package identifier of the
     * package hosting the activity to be launched, which is specified in the AndroidManifest.xml
     * file.  This is not necessarily the same as the java package name of the class - in fact, in
     * some cases it may not match at all.
     */
    public ConfigureMorseActivityTest() {
        super("com.simpligility.android.morseflash", ConfigureMorseActivity.class);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        final ConfigureMorseActivity a = getActivity();
        // ensure a valid handle to the activity has been returned
        assertNotNull(a);
        enterButton = (Button) a.findViewById(R.id.morse);
    }
    
    @MediumTest
    public void testPressingEnterButton()  {
        // Give right button focus by having it request focus.  We post it
        // to the UI thread because we are not running on the same thread, and
        // any direct api calls that change state must be made from the UI thread.
        // This is in contrast to instrumentation calls that send events that are
        // processed through the framework and eventually find their way to
        // affecting the ui thread.
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                enterButton.requestFocus();
            }
        });
        // wait for the request to go through
        getInstrumentation().waitForIdleSync();

        assertTrue(enterButton.isFocused());
        
        

//        sendKeys(KeyEvent.KEYCODE_DPAD_LEFT);
//        assertTrue("center button should be focused", mCenterButton.isFocused());
    }
    

}