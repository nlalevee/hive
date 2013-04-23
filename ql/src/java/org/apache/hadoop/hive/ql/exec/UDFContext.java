/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hive.ql.exec;

import org.apache.hadoop.mapred.JobConf;

/**
 * UDFContext.
 *
 * A singleton class that provides UDFs with runtime
 * context.
 */
@SuppressWarnings("deprecation")
public class UDFContext {
  private static final UDFContext INSTANCE = new UDFContext();

  private JobConf conf = null;

  private UDFContext() {}

  /**
   * Sets the JobConf for this singleton. This is called on
   * the backend as part of the initialization of the
   * Map and Reduce functions in order to provide UDFs with
   * runtime access to the JobConf.
   * @param conf
   */
  public static void addJobConf(JobConf conf) {
    INSTANCE.conf = conf;
  }

  /**
   * Get a copy of the JobConf. This method should only be
   * called by UDFs executing on the backend. This method will
   * return NULL if called on the frontend.
   * @return A copy of the JobConf for this job.
   */
  public static JobConf getJobConf() {
    if (null != INSTANCE.conf) {
      return new JobConf(INSTANCE.conf);
    }
    return null;
  }
}
