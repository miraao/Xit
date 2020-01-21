// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package jp.pixela.player_service.tunerservice;

import android.os.Parcel;
import android.os.Parcelable;

public class XmppAccount
    implements Parcelable
{

    public XmppAccount()
    {
    }

    public XmppAccount(Parcel parcel)
    {
        xmppServerName_ = parcel.readString();
        port_ = parcel.readString();
        userName_ = parcel.readString();
        password_ = parcel.readString();
        resourceId_ = parcel.readString();
    }

    public int describeContents()
    {
        return 0;
    }

    public String getPassword()
    {
        return password_;
    }

    public String getPort()
    {
        return port_;
    }

    public String getResourceId()
    {
        return resourceId_;
    }

    public String getUserName()
    {
        return userName_;
    }

    public String getXmppServerName()
    {
        return xmppServerName_;
    }

    public void setPassword(String s)
    {
        password_ = s;
    }

    public void setPort(String s)
    {
        port_ = s;
    }

    public void setResourceId(String s)
    {
        resourceId_ = s;
    }

    public void setUserName(String s)
    {
        userName_ = s;
    }

    public void setXmppServerName(String s)
    {
        xmppServerName_ = s;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(xmppServerName_);
        parcel.writeString(port_);
        parcel.writeString(userName_);
        parcel.writeString(password_);
        parcel.writeString(resourceId_);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public XmppAccount createFromParcel(Parcel parcel)
        {
            return new XmppAccount(parcel);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public XmppAccount[] newArray(int i)
        {
            return new XmppAccount[i];
        }

    }
;
    private String password_;
    private String port_;
    private String resourceId_;
    private String userName_;
    private String xmppServerName_;

}
