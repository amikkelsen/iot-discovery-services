package com.verisign.iot.discovery.utils;

import com.verisign.iot.discovery.domain.Fqdn;

/**
 * A set of static utils to handle message formatting.
 *
 * @author pmaresca
 * @version 1.0
 * @since May 02, 2015
 */
public final class FormattingUtil
{

	/**
	 * Query output template.
	 */
	private static final String QUERY_OUTPUT = ";; QUERY\n%s %s";
	/**
	 * Response output template.
	 */
	// TODO TTL, TYPE and other attributes to be split
	private static final String SIMPLE_RESPONSE_OUTPUT = "%s";
	/**
	 * Response output template (decorated with a header).
	 */
	// TODO TTL, TYPE and other attributes to be split
	private static final String RESPONSE_OUTPUT = ";; RESPONSE\n%s\n";
	/**
	 * Server output template.
	 */
	private static final String SERVER_OUTPUT = ";; SERVER\n%s\n";


	/**
	 * Build up a formatted message for resolver server.
	 *
	 * @param server A host's IP/Hostname
	 * @return A <code>String</code> containing the server display
	 */
	public static String server ( String server )
    {
		return String.format( SERVER_OUTPUT, server );
	}


	/**
	 * Build up a formatted message for a DNS query.
	 *
	 * @param name   A <code>Fqdn</code> to be looked up
	 * @param prefix A <code>String</code> representing the domain name prefix
	 * @param type   A <code>String</code> containing the resource record type
	 * @return A <code>String</code> containing the query content
	 */
	public static String query ( Fqdn name, String prefix, String type )
    {
		return String.format( QUERY_OUTPUT, name.fqdnWithPrefix( prefix ), type );
	}


	/**
	 * Build up a formatted response message.
	 *
	 * @param content A <code>String</code> wrapping the content
	 * @return A formatted <code>String</code>
	 */
	public static String simpleResponse ( String content )
    {
		return String.format( SIMPLE_RESPONSE_OUTPUT, content );
	}


	/**
	 * Build up a formatted response message, decorated with a response header.
	 *
	 * @param content A <code>String</code> wrapping the content
	 * @return A formatted <code>String</code>
	 */
	public static String response ( String content )
    {
		return String.format( RESPONSE_OUTPUT, content );
	}

    /**
     * Formats a resolution problem.
     *
     * @param what  The identifier of the unresolved entity
     *
     * @return  The formatted message
     */
    public static String unableToResolve(String what)
    {
        return String.format("Unable to resolve [%s]", what);
    }

    /**
     * Formats a validation problem.
     *
     * @param what  The identifier of the unresolved entity
     *
     * @return  The formatted message
     */
    public static String unableToValidate(String what)
    {
        return String.format("Unable to authenticate [%s]: network/server error", what);
    }

    /**
     * Formats an authentication problem.
     *
     * @param which  The identifier of the unresolved entity
     *
     * @return  The formatted message
     */
    public static String authenticData(String which)
    {
        return String.format("Received authentic data for [%s]", which);
    }

    /**
     * Formats a network problem.
     *
     * @param what  The identifier of the unresolved entity
     *
     * @return  The formatted message
     */
    public static String networkError(String what)
    {
        return String.format("Experienced a network errror [%s]", what);
    }

    /**
     * Formats a resolution problem: DNS label.
     *
     * @param forWhat   The identifier of the problematic entity
     *
     * @return  The formatted message
     */
    public static String unableToRetrieveLabel(String forWhat)
    {
        return String.format("Unable to retrieve the DNS label [%s]", forWhat);
    }


	private FormattingUtil ()
    {
		throw new AssertionError( String.format( "No instances of %s for you!",
				this.getClass().getName() ) );
	}

}
