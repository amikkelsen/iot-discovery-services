package com.verisign.iot.discovery.domain;

import com.verisign.iot.discovery.commons.Constants;
import com.verisign.iot.discovery.utils.ValidatorUtil;

/**
 * Created by tjmurphy on 6/4/15.
 */
public class TLSAPrefix
{

    private int port;
    private String protocol;

    public TLSAPrefix()
    {
        setDefaultValues();
    }

    public TLSAPrefix(String initString)
    {
        parseInitString(initString);
    }

    public void reinitialize()
    {
        reinitialize(null);
    }

    public void reinitialize(String initString)
    {
        parseInitString(initString);
    }

    public int getPort()
    {
        return port;
    }

    public String getProtocol()
    {
        return protocol;
    }

    private void parseInitString(String initString)
    {
        if (initString == null || initString.trim().isEmpty()) {
            setDefaultValues();
        } else {
            initString = initString.trim();
            int delimiterIndex = initString.indexOf(Constants.COLON_UNICODE_CHAR);
            if (delimiterIndex == -1) {
                protocol = Constants.TLSA_DEFAULT_PROTOCOL;
                if (ValidatorUtil.isValidPort(initString)) {
                    port = Integer.parseInt(initString);
                } else {
                    throw new IllegalArgumentException("invalid port value");
                }
            } else if (delimiterIndex == 0) {
                port = Constants.TLSA_DEFAULT_PORT;
                protocol = initString.substring(1);
            } else {
                String portString = initString.substring(0, delimiterIndex);
                if (ValidatorUtil.isValidPort(portString)) {
                    port = Integer.parseInt(portString);
                } else {
                    throw new IllegalArgumentException("invalid port value");
                }
                protocol = initString.substring(delimiterIndex + 1);
                protocol = protocol.length() > 0 ? protocol : Constants.TLSA_DEFAULT_PROTOCOL;
            }
        }
    }

    private void setDefaultValues()
    {
        port = Constants.TLSA_DEFAULT_PORT;
        protocol = Constants.TLSA_DEFAULT_PROTOCOL;
    }

    @Override
    public String toString()
    {
        return "_" + port + Constants.DNS_LABEL_DELIMITER + "_" + protocol;
    }

}
