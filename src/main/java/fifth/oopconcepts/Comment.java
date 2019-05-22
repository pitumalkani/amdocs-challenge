package fifth.oopconcepts;

import java.util.Date;
import java.util.Objects;

/**
 * The Class Comment.
 */
public class Comment {

    /** The message. */
    private String message;

    /** The author. */
    private User author;

    /** The created at. */
    private Date createdAt;

    /** The replied to. */
    private Comment repliedTo;

    /**
     * Instantiates a new comment.
     *
     * @param user the user
     * @param message the message
     */
    public Comment( User user, String message ) {
        this.author = user;
        this.message = message;
        this.createdAt = new Date();
    }

    /**
     * Instantiates a new comment.
     *
     * @param user the user
     * @param message the message
     * @param repliedTo the replied to
     */
    public Comment( User user, String message, Comment repliedTo ) {
        this( user, message );
        this.repliedTo = repliedTo;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the message to set
     */
    public void setMessage( String message ) {
        this.message = message;
    }

    /**
     * Gets the author.
     *
     * @return the author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Gets the created at.
     *
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the replied to.
     *
     * @return the replied to
     */
    public Comment getRepliedTo() {
        return repliedTo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( message );
        builder.append( " by " );
        builder.append( author.getName() );
        if ( Objects.nonNull( repliedTo ) ) {
            builder.append( " (replied to " );
            builder.append( repliedTo.getAuthor().getName() );
            builder.append( ")" );
        }
        return builder.toString();
    }

}
