/*
 * Copyright 2017 Apereo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tle.core.db.tables

import java.time.Instant

import com.tle.core.db.types.{DbUUID, InstId}

case class ItemViewCount(inst: InstId, item_uuid: DbUUID, item_version: Int, count: Int, last_viewed: Instant)

case class AttachmentViewCount(inst: InstId, item_uuid: DbUUID, item_version: Int, attachment: DbUUID, count: Int, last_viewed: Instant)