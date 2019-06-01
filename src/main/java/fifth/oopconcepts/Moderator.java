package fifth.oopconcepts;

public class Moderator extends User {

    public Moderator( String userName ) {
       super(userName);
    }

    public boolean canEdit( Comment comment ) {
        return super.canEdit( comment );
    }

    public boolean canDelete( Comment comment ) {
            return true;
    }
}
