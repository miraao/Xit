// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;


public final class NetworkInterfaceModeT extends Enum
{

    private NetworkInterfaceModeT(String s, int i)
    {
        super(s, i);
    }

    public static NetworkInterfaceModeT valueOf(String s)
    {
        return (NetworkInterfaceModeT)Enum.valueOf(jp/pixela/player_service/tunerservice/NetworkInterfaceModeT, s);
    }

    public static NetworkInterfaceModeT[] values()
    {
        return (NetworkInterfaceModeT[])$VALUES.clone();
    }

    private static final NetworkInterfaceModeT $VALUES[];
    public static final NetworkInterfaceModeT NetworkInterface_BR310L;
    public static final NetworkInterfaceModeT NetworkInterface_BR310W;
    public static final NetworkInterfaceModeT NetworkInterface_DT195;
    public static final NetworkInterfaceModeT NetworkInterface_Fj;
    public static final NetworkInterfaceModeT NetworkInterface_PCATS001;
    public static final NetworkInterfaceModeT NetworkInterface_PCATS002;
    public static final NetworkInterfaceModeT NetworkInterface_POE_MP4000;
    public static final NetworkInterfaceModeT NetworkInterface_Retail;
    public static final NetworkInterfaceModeT NetworkInterface_YBB;

    static 
    {
        NetworkInterface_Retail = new NetworkInterfaceModeT("NetworkInterface_Retail", 0);
        NetworkInterface_YBB = new NetworkInterfaceModeT("NetworkInterface_YBB", 1);
        NetworkInterface_Fj = new NetworkInterfaceModeT("NetworkInterface_Fj", 2);
        NetworkInterface_BR310L = new NetworkInterfaceModeT("NetworkInterface_BR310L", 3);
        NetworkInterface_BR310W = new NetworkInterfaceModeT("NetworkInterface_BR310W", 4);
        NetworkInterface_PCATS001 = new NetworkInterfaceModeT("NetworkInterface_PCATS001", 5);
        NetworkInterface_DT195 = new NetworkInterfaceModeT("NetworkInterface_DT195", 6);
        NetworkInterface_PCATS002 = new NetworkInterfaceModeT("NetworkInterface_PCATS002", 7);
        NetworkInterface_POE_MP4000 = new NetworkInterfaceModeT("NetworkInterface_POE_MP4000", 8);
        $VALUES = (new NetworkInterfaceModeT[] {
            NetworkInterface_Retail, NetworkInterface_YBB, NetworkInterface_Fj, NetworkInterface_BR310L, NetworkInterface_BR310W, NetworkInterface_PCATS001, NetworkInterface_DT195, NetworkInterface_PCATS002, NetworkInterface_POE_MP4000
        });
    }
}
