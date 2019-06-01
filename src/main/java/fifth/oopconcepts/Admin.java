package fifth.oopconcepts;

public class Admin extends Moderator {

    public Admin( String userName ) {
        super( userName );
    }

    public boolean canEdit( Comment comment ) {
            return true;
    }

    public boolean canDelete( Comment comment ) {
            return true;
    }
}
