/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.fontbox.ttf.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 
 * A {@link Map} based simple implementation of the {@link ScriptFeature}
 * 
 * @author Palash Ray
 *
 */
public class MapBackedScriptFeature implements ScriptFeature
{

    private final String name;
    private final Map<List<Integer>, Integer> featureMap;

    public MapBackedScriptFeature(String name, Map<List<Integer>, Integer> featureMap)
    {
        this.name = name;
        this.featureMap = featureMap;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public Set<List<Integer>> getAllGlyphIdsForSubstitution()
    {
        return featureMap.keySet();
    }

    @Override
    public boolean canReplaceGlyphs(List<Integer> glyphIds)
    {
        return featureMap.containsKey(glyphIds);
    }

    @Override
    public Integer getReplacementForGlyphs(List<Integer> glyphIds)
    {
        if (!canReplaceGlyphs(glyphIds))
        {
            throw new UnsupportedOperationException(
                    "The glyphs " + glyphIds + " cannot be replaced");
        }
        return featureMap.get(glyphIds);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(featureMap, name);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        MapBackedScriptFeature other = (MapBackedScriptFeature) obj;
        return Objects.equals(name, this.name) && Objects.equals(featureMap, this.featureMap);
    }
}
