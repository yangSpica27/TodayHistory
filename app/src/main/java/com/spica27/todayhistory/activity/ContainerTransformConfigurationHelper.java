/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.spica27.todayhistory.activity;

import android.os.Build.VERSION_CODES;
import android.util.SparseIntArray;
import android.view.animation.Interpolator;
import androidx.annotation.RequiresApi;
import com.google.android.material.transition.MaterialArcMotion;
import com.google.android.material.transition.MaterialContainerTransform;
import com.spica27.todayhistory.R;

@RequiresApi(VERSION_CODES.LOLLIPOP)
public class ContainerTransformConfigurationHelper {

  private static final String CUBIC_CONTROL_FORMAT = "%.3f";
  private static final String DURATION_FORMAT = "%.0f";

  protected boolean isArcMotionEnabled;
  private long enterDuration = 275;
  private long returnDuration = 195;
  private Interpolator interpolator;
  private int fadeModeButtonId = R.id.fab_fav;
  private boolean drawDebugEnabled;

  private static final SparseIntArray FADE_MODE_MAP = new SparseIntArray();

  static {
    FADE_MODE_MAP.append(R.id.fab_fav, MaterialContainerTransform.FADE_MODE_IN);
  }

  void configure(MaterialContainerTransform transform, boolean entering) {
    transform.setDuration(entering ? getEnterDuration() : getReturnDuration());
    transform.setInterpolator(getInterpolator());
    if (isArcMotionEnabled()) {
      transform.setPathMotion(new MaterialArcMotion());
    }
    if (entering) {
      transform.setDuration(getEnterDuration());
    } else {
      transform.setDuration(getReturnDuration());
    }

    transform.setFadeMode(MaterialContainerTransform.FADE_MODE_THROUGH);
    transform.setDrawDebugEnabled(isDrawDebugEnabled());
  }

  boolean isArcMotionEnabled() {
    return isArcMotionEnabled;
  }

  long getEnterDuration() {
    return enterDuration;
  }

  long getReturnDuration() {
    return returnDuration;
  }

  Interpolator getInterpolator() {
    return interpolator;
  }

  int getFadeMode() {
    return FADE_MODE_MAP.get(fadeModeButtonId);
  }

  boolean isDrawDebugEnabled() {
    return drawDebugEnabled;
  }

  protected void setDefaultMotionPath() {
    isArcMotionEnabled = false;
  }
}
