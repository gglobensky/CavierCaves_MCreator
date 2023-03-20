package net.gglobensky.caviercaves.utils;

import com.mojang.math.Vector3d;
import net.gglobensky.caviercaves.enums.Orientation;

import java.util.HashMap;

import static javax.swing.UIManager.put;

public class Vector3 {
    public static Vector3d UP = new Vector3d(0, 1, 0);
    public static Vector3d DOWN = new Vector3d(0, -1, 0);
    public static Vector3d EAST = new Vector3d(1, 0, 0);
    public static Vector3d WEST = new Vector3d(-1, 0, 0);
    public static Vector3d NORTH = new Vector3d(0, 0, 1);
    public static Vector3d SOUTH = new Vector3d(0, 0, -1);

    public static HashMap<Orientation, Vector3d> orientations = new HashMap<Orientation, Vector3d>()
            {{
                put(Orientation.UP, UP);
                put(Orientation.DOWN, DOWN);
                put(Orientation.EAST, EAST);
                put(Orientation.WEST, WEST);
                put(Orientation.NORTH, NORTH);
                put(Orientation.SOUTH, SOUTH);
            }};

    public static Vector3d rotateVector(Vector3d vec, Vector3d axis, double theta){
        double x, y, z;
        double u, v, w;
        x=vec.x;y=vec.y;z=vec.z;
        u=axis.x;v=axis.y;w=axis.z;
        double v1 = u * x + v * y + w * z;
        double t = 1d - Math.cos(theta);
        double s = Math.sin(theta);
        double c = Math.cos(theta);

        double xPrime = u * v1 * t
                + x * c
                + (-w * y + v * z) * s;
        double yPrime = v * v1 * t
                + y * c
                + (w * x - u * z) * s;
        double zPrime = w * v1 * t
                + z * c
                + (-v * x + u * y) * s;
        return new Vector3d(xPrime, yPrime, zPrime);
    }
}
