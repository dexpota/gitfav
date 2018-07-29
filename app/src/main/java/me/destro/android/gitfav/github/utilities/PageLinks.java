package me.destro.android.gitfav.github.utilities;

import okhttp3.Headers;

/**
 * Page link class to be used to determine the links to other pages of request
 * responses encoded in the current response. These will be present if the
 * result set size exceeds the per page limit.
 */
public class PageLinks {

	private static final String HEADER_LINK = "Link"; //$NON-NLS-1$
	private static final String HEADER_NEXT = "X-Next"; //$NON-NLS-1$
	private static final String HEADER_LAST = "X-Last"; //$NON-NLS-1$

	private static final String META_REL = "rel"; //$NON-NLS-1$
	private static final String META_LAST = "last"; //$NON-NLS-1$
	private static final String META_NEXT = "next"; //$NON-NLS-1$
	private static final String META_FIRST = "first"; //$NON-NLS-1$
	private static final String META_PREV = "prev"; //$NON-NLS-1$

	private static final String DELIM_LINKS = ","; //$NON-NLS-1$

	private static final String DELIM_LINK_PARAM = ";"; //$NON-NLS-1$

	private String first;
	private String last;
	private String next;
	private String prev;

	public PageLinks(Headers headers) {
		String linkHeader = headers.get(HEADER_LINK);;
		if (linkHeader != null) {
			String[] links = linkHeader.split(DELIM_LINKS);
			for (String link : links) {
				String[] segments = link.split(DELIM_LINK_PARAM);
				if (segments.length < 2)
					continue;

				String linkPart = segments[0].trim();
				if (!linkPart.startsWith("<") || !linkPart.endsWith(">")) //$NON-NLS-1$ //$NON-NLS-2$
					continue;
				linkPart = linkPart.substring(1, linkPart.length() - 1);

				for (int i = 1; i < segments.length; i++) {
					String[] rel = segments[i].trim().split("="); //$NON-NLS-1$
					if (rel.length < 2 || !META_REL.equals(rel[0]))
						continue;

					String relValue = rel[1];
					if (relValue.startsWith("\"") && relValue.endsWith("\"")) //$NON-NLS-1$ //$NON-NLS-2$
						relValue = relValue.substring(1, relValue.length() - 1);

					if (META_FIRST.equals(relValue))
						first = linkPart;
					else if (META_LAST.equals(relValue))
						last = linkPart;
					else if (META_NEXT.equals(relValue))
						next = linkPart;
					else if (META_PREV.equals(relValue))
						prev = linkPart;
				}
			}
		} else {
			next = headers.get(HEADER_NEXT);
			last = headers.get(HEADER_LAST);
		}
	}

	/**
	 * @return first
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * @return last
	 */
	public String getLast() {
		return last;
	}

	/**
	 * @return next
	 */
	public String getNext() {
		return next;
	}

	/**
	 * @return prev
	 */
	public String getPrev() {
		return prev;
	}
}