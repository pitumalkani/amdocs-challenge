package fifth.oopconcepts;

public class Admin extends Moderator {

    public Admin( String userName ) {
        super( userName );
    }

    public boolean canEdit( Comment comment ) {
        if ( comment.getAuthor() instanceof User || comment.getAuthor() instanceof Moderator || comment.getAuthor() instanceof Admin ) {
            return true;
        }
        return false;
    }

    public boolean canDelete( Comment comment ) {
        if ( comment.getAuthor() instanceof User || comment.getAuthor() instanceof Moderator || comment.getAuthor() instanceof Admin ) {
            return true;
        }
        return false;
    }
}
