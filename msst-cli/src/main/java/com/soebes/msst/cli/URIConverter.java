package com.soebes.msst.cli;

import java.net.URI;
import java.net.URISyntaxException;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;

/**
 * This converter will convert a string from a command
 * line parameter into an {@link URI}.
 *
 * @author Karl Heinz Marbaise
 *
 */
public class URIConverter implements IStringConverter<URI> {

    @Override
    public URI convert(String value) {
        URI uri;
        try {
            uri = new URI(value);
        } catch (URISyntaxException e) {
            throw new ParameterException("Wrong URL given (" + e.getMessage() + ")");
        }
        return uri;
    }
}
