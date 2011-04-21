package com.googlecode.rd.serverdriver.matchers;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.googlecode.rd.serverdriver.http.response.Response;
import com.googlecode.rd.types.Header;

/**
 * TODO: Is this class necessary we can do:
 * 
 * assertThat(response.getHeaders(), hasItem(new Header("header", "value")))
 */
public class HasHeader extends TypeSafeMatcher<Response> {

	private final String name;

	public HasHeader(final String name) {
		this.name = name;
	}

	@Override
	protected final boolean matchesSafely(final Response response) {

		for (final Header header : response.getHeaders()) {
			if (StringUtils.equals(header.getName(), name)) {
				return true;
			}
		}

		return false;

	}

	@Override
	public final void describeTo(final Description description) {
		description.appendText("Response with header named '" + name + "'");
	}

	@Override
	protected final void describeMismatchSafely(final Response response, final Description mismatchDescription) {
		final List<Header> headers = response.getHeaders();

		if (headers.isEmpty()) {
			mismatchDescription.appendText("Response has no headers");
		} else {
			mismatchDescription.appendText("Response has headers [" + StringUtils.join(response.getHeaders(), ",") + "]");
		}
	}

}