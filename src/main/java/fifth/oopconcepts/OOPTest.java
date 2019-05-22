package fifth.oopconcepts;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;

public class OOPTest {
    User user1;
    User user2;
    Moderator mod;
    Admin admin;
    Comment user1Comment;
    Comment user2Comment;
    Comment modComment;

    void createUsers() {
        user1 = new User( "User 1" );
        user2 = new User( "User 2" );
    }

    void createModerators() {
        mod = new Moderator( "Moderator" );
        admin = new Admin( "Admin" );
    }

    void createComments() {
        createUsers();
        createModerators();
        user1Comment = new Comment( user1, "hello" );
        user2Comment = new Comment( user2, "hi", user1Comment );
        modComment = new Comment( mod, "moderator", user2Comment );
    }

    @Test
    public void testInstantiation() throws Exception {
        createComments();
        assertEquals( "User name is set correctly:", "User 1", user1.getName() );
        assertEquals( "User name is set correctly:", "User 2", user2.getName() );
        assertThat( "Comment date was set on creation:", user1Comment.getCreatedAt(), is( Date.class ) );
        assertSame( "User was set on comment creation:", user1, user1Comment.getAuthor() );
        assertSame( "User was set on comment creation:", user2, user2Comment.getAuthor() );
        assertSame( "User was set on comment creation:", mod, modComment.getAuthor() );
    }

    @Test
    public void testLoginMethods() throws Exception {
        createUsers();
        assertEquals( "User is logged out by default:", false, user1.isLoggedIn() );
        assertNull( "Last logged in date is not set by default:", user1.getLastLoggedInAt() );
        user1.logIn();
        assertEquals( "User can be logged in:", true, user1.isLoggedIn() );
        assertThat( "Last logged in date is set:", user1.getLastLoggedInAt(), is( Date.class ) );
        Date lastLoggedInDate = user1.getLastLoggedInAt();
        user1.logOut();
        assertEquals( "User can be logged out:", false, user1.isLoggedIn() );
        assertEquals( "Last logged in date stays the same:", lastLoggedInDate, user1.getLastLoggedInAt() );
    }

    @Test
    public void testInheritance() throws Exception {
        createUsers();
        createModerators();
        assertThat( "User is a User:", user1, is( User.class ) );
        assertThat( "User is not a Moderator:", user1, is( not( Moderator.class ) ) );
        assertThat( "User is not an Admin:", user1, is( not( Admin.class ) ) );

        assertThat( "Moderator is a User:", mod, is( User.class ) );
        assertThat( "Moderator is a Moderator:", mod, is( Moderator.class ) );
        assertThat( "Moderator is a not an Admin:", mod, is( not( Admin.class ) ) );

        assertThat( "Admin is a User:", admin, is( User.class ) );
        assertThat( "Admin is a Moderator:", admin, is( Moderator.class ) );
        assertThat( "Admin is an Admin:", admin, is( Admin.class ) );
    }

    @Test
    public void testMethodOverriding() throws Exception {
        createComments();
        assertEquals( "User can edit their own comment:", true, user1.canEdit( user1Comment ) );
        assertEquals( "User cannot edit other comments:", false, user1.canEdit( user2Comment ) );
        assertEquals( "User cannot delete their own comment:", false, user1.canDelete( user1Comment ) );
        assertEquals( "User cannot delete other comment:", false, user1.canDelete( user1Comment ) );

        assertEquals( "Moderator can edit their own comment:", true, mod.canEdit( modComment ) );
        assertEquals( "Moderator cannot edit other comments:", false, mod.canEdit( user1Comment ) );
        assertEquals( "Moderator can delete their own comment:", true, mod.canDelete( modComment ) );
        assertEquals( "Moderator can delete other comments:", true, mod.canDelete( user1Comment ) );

        assertEquals( "Admin can edit other comments:", true, admin.canEdit( user1Comment ) );
        assertEquals( "Admin can delete other comments:", true, admin.canDelete( user1Comment ) );
    }

    @Test
    public void testMethodOverloading() throws Exception {
        createComments();
        assertNull( "Comment was created without a reply:", user1Comment.getRepliedTo() );
        assertEquals( "Comment was created with a reply:", user1Comment, user2Comment.getRepliedTo() );
    }

    @Test
    public void testUserEncapsulation() throws Exception {
        createUsers();
        assertEquals( "User name was set:", "User 1", user1.getName() );
        user1.setName( "User 1 updated" );
        assertEquals( "User name can be updated:", "User 1 updated", user1.getName() );
        Class<?> c = user1.getClass();

        java.lang.reflect.Field[] fields = c.getFields();
        assertEquals( "There should be no public fields on the User class:", 0, c.getFields().length );
    }

    @Test
    public void testCommentEncapsulation() throws Exception {
        createComments();
        assertEquals( "Comment message was set:", "hello", user1Comment.getMessage() );
        user1Comment.setMessage( "howdy" );
        assertEquals( "Comment message can be updated:", "howdy", user1Comment.getMessage() );
        Class<?> c = user1Comment.getClass();

        java.lang.reflect.Field[] fields = c.getFields();
        assertEquals( "There should be no public fields on the Comment class:", 0, c.getFields().length );
    }

    @Test
    public void testComposition() throws Exception {
        createComments();
        assertSame( "Ensure comment relationships are preserved:", user1Comment, user2Comment.getRepliedTo() );
        assertSame( "Ensure comment relationships are preserved:", user2Comment, modComment.getRepliedTo() );
    }

    @Test
    public void testToString() throws Exception {
    createComments();
    assertEquals("The toString method should return the correct hierarchy (no reply):", "hello by User 1", user1Comment.toString());
    assertEquals("The toString method should return the correct hierarchy (reply):", "hi by User 2 (replied to User 1)", user2Comment.toString());
    assertEquals("The toString method should return the correct hierarchy (nested reply):" ,
   "moderator by Moderator (replied to User 2)", modComment.toString());

    user1.setName("User One");
    user2.setName("User Two");
    assertEquals("The toString method should reflect reference changes:", "hello by User One",
   user1Comment.toString());
    assertEquals("The toString method should reflect reference changes:", "hi by User Two (replied to User One)", user2Comment.toString());
    }
}
