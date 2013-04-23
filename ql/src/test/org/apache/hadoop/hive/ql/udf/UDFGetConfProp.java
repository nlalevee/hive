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

package org.apache.hadoop.hive.ql.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.exec.UDFContext;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;

/**
 * UDFGetConfProp.
 *
 * Maps a configuration property name to its
 * corresponding value using a copy of the JobConf
 * obtained from the UDFContext.
 */
public class UDFGetConfProp extends UDF {

  private final Text result = new Text();
  private boolean isInitialized = false;
  private JobConf conf = null;

  public Text evaluate(Text propName) {
    if (!isInitialized) {
      conf = UDFContext.getJobConf();
      isInitialized = true;
    }
    if (propName == null || conf == null) {
        return null;
    }
    result.set(conf.get(propName.toString()));
    return result;
  }
}
