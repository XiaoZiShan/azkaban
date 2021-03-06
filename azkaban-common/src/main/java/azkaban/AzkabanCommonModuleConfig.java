/*
 * Copyright 2017 LinkedIn Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */

package azkaban;

import static azkaban.Constants.ConfigurationKeys.*;
import static azkaban.storage.StorageImplementationType.DATABASE;

import azkaban.storage.StorageImplementationType;
import azkaban.utils.Props;
import javax.inject.Inject;
import java.net.URI;
import org.apache.log4j.Logger;


public class AzkabanCommonModuleConfig {

  private static final Logger log = Logger.getLogger(AzkabanCommonModuleConfig.class);

  private final Props props;
  private final URI hdfsProjectRootUri;
  private final URI cacheDependencyRootUri;
  private final URI originDependencyRootUri;
  private final boolean dependencyCachingEnabled;
  /**
   * Storage Implementation This can be any of the {@link StorageImplementationType} values in which
   * case {@link StorageFactory} will create the appropriate storage instance. Or one can feed in a
   * custom implementation class using the full qualified path required by a classloader.
   *
   * examples: LOCAL, DATABASE, azkaban.storage.MyFavStorage
   */
  private String storageImplementation = DATABASE.name();
  private String localStorageBaseDirPath = "./local/storage";

  @Inject
  public AzkabanCommonModuleConfig(final Props props) {
    this.props = props;

    this.storageImplementation = props.getString(AZKABAN_STORAGE_TYPE, this.storageImplementation);
    this.localStorageBaseDirPath = props.getString(AZKABAN_STORAGE_LOCAL_BASEDIR, this.localStorageBaseDirPath);
    this.hdfsProjectRootUri = props.getUri(AZKABAN_STORAGE_HDFS_PROJECT_ROOT_URI, null, true);
    this.cacheDependencyRootUri = props.getUri(AZKABAN_STORAGE_CACHE_DEPENDENCY_ROOT_URI, null, true);
    this.originDependencyRootUri = props.getUri(AZKABAN_STORAGE_ORIGIN_DEPENDENCY_ROOT_URI, null, true);
    this.dependencyCachingEnabled = props.getBoolean(AZKABAN_STORAGE_CACHE_DEPENDENCY_ENABLED, true);
  }

  public Props getProps() {
    return this.props;
  }

  public String getStorageImplementation() {
    return this.storageImplementation;
  }

  public String getLocalStorageBaseDirPath() {
    return this.localStorageBaseDirPath;
  }

  public URI getHdfsProjectRootUri() {
    return this.hdfsProjectRootUri;
  }

  public URI getCacheDependencyRootUri() {
    return this.cacheDependencyRootUri;
  }

  public URI getOriginDependencyRootUri() {
    return this.originDependencyRootUri;
  }

  public boolean getDependencyCachingEnabled() {
    return this.dependencyCachingEnabled; }
}
