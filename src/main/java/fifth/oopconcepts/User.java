package fifth.oopconcepts;

import java.util.Date;

public class User {

    public User( String userName ) {
        setName( userName );
    }

    private String name;

    private boolean loggedIn;

    private Date lastLoggedInAt;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * @return the loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @return the lastLoggedInAt
     */
    public Date getLastLoggedInAt() {
        return lastLoggedInAt;
    }

    public boolean canEdit( Comment comment ) {
        if ( this.equals( comment.getAuthor() ) ) {
            return true;
        }
        return false;
    }

    public boolean canDelete( Comment comment ) {
        return false;
    }

    public void logIn() {
        this.loggedIn = true;
        this.lastLoggedInAt = new Date();
    }

    public void logOut() {
        this.loggedIn = false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lastLoggedInAt == null) ? 0 : lastLoggedInAt.hashCode());
        result = prime * result + (loggedIn ? 1231 : 1237);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( !(obj instanceof User) )
            return false;
        User other = (User)obj;
        if ( lastLoggedInAt == null ) {
            if ( other.lastLoggedInAt != null )
                return false;
        } else if ( !lastLoggedInAt.equals( other.lastLoggedInAt ) )
            return false;
        if ( loggedIn != other.loggedIn )
            return false;
        if ( name == null ) {
            if ( other.name != null )
                return false;
        } else if ( !name.equals( other.name ) )
            return false;
        return true;
    }
}
