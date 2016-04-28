/*
 * Copyright 2014 Geoff Capper.
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

package resources.gmapsfx.shapes;

import resources.gmapsfx.javascript.object.GMapObjectType;
import resources.gmapsfx.javascript.object.LatLongBounds;
import resources.gmapsfx.javascript.object.MapShape;

/**
 *
 * @author Geoff Capper
 */
public class Rectangle extends MapShape {
    
    public Rectangle() {
        super(GMapObjectType.RECTANGLE);
    }
    
    public Rectangle(RectangleOptions opts) {
        super(GMapObjectType.RECTANGLE, opts);
    }
    
    //setBounds
    public void setBounds(LatLongBounds bounds) {
        invokeJavascript("setBounds", bounds);
    }
    
}
