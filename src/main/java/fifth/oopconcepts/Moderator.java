package fifth.oopconcepts;

public class Moderator extends User {

    public Moderator( String userName ) {
       super(userName);
    }

    public boolean canEdit( Comment comment ) {
        if ( this.equals( comment.getAuthor() ) ) {
            return true;
        }
        return false;
    }

    public boolean canDelete( Comment comment ) {
        if ( comment.getAuthor() instanceof User || comment.getAuthor() instanceof Admin  ) {
            return true;
        }
        return false;
    }
}
