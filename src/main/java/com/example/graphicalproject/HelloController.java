package com.example.graphicalproject;

import controller.Login;
import controller.SignUp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import controller.User;
import model.SQL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class HelloController {

    Stage stage = new Stage();
    Scene scene;
    Image icon = new Image("logo.png");
    Image themeImage;
    @FXML
    TextField userNameInLoginPage;
    @FXML
    PasswordField passwordInLoginPage;
    @FXML
    Label messageInLoginPage;
    @FXML
    public void login() throws SQLException,IOException
    {
        if(controller.Login.login(userNameInLoginPage.getCharacters().toString(),passwordInLoginPage.getCharacters().toString()).equals("Username doesn't exist!") || controller.Login.login(userNameInLoginPage.getCharacters().toString(),passwordInLoginPage.getCharacters().toString()).equals("Password is wrong!"))
        {
            messageInLoginPage.setTextFill(Color.RED);
            messageInLoginPage.setText(Login.login(userNameInLoginPage.getCharacters().toString(),passwordInLoginPage.getCharacters().toString()));
        }
        else
        {
            messageInLoginPage.setTextFill(Color.GREEN);
            messageInLoginPage.setText(Login.login(userNameInLoginPage.getCharacters().toString(),passwordInLoginPage.getCharacters().toString()));
            stage = (Stage) messageInLoginPage.getScene().getWindow();
            stage.close();
            User.myUserName = userNameInLoginPage.getCharacters().toString();
            User.myID = SQL.getIDByUserName(User.myUserName);
            User.businessAccount = SQL.getBoolADByID(User.myID);
            User.profileImage = SQL.getProfileImageByID(User.myID);
            User.postsIndexInHomePage = SQL.getHomePagePostsIDByID(User.myID).size()-1;
            if(User.postsIndexInHomePage!=-1)
            {
                User.commentIndexInHomePage = SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).size() - 1;
                if (User.commentIndexInHomePage != -1)
                {
                    User.replyIndexInHomePage = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)).size() - 1;
                }
                if (SQL.getBoolADByID(SQL.getUserIDByContentID(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage))))
                {
                    SQL.postView(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage));
                }
            }
            homePage();
        }
    }
    @FXML
    Button homePageInMyPage;
    @FXML
    public void fromMyPageToHomePage() throws IOException,SQLException
    {
        stage = (Stage) homePageInMyPage.getScene().getWindow();
        stage.close();
        User.postsIndexInHomePage = SQL.getHomePagePostsIDByID(User.myID).size()-1;
        if(User.postsIndexInHomePage!=-1)
        {
            User.commentIndexInHomePage = SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).size() - 1;
            if (User.commentIndexInHomePage != -1)
            {
                User.replyIndexInHomePage = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)).size() - 1;
            }
        }
        if(SQL.getBoolADByID(SQL.getUserIDByContentID(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage))) && User.postsIndexInHomePage!=-1)
        {
            SQL.postView(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage));
        }
        homePage();
    }
    @FXML
    Button homePageInSettingsPage;
    @FXML
    public void fromSettingsPageToHomePage() throws IOException,SQLException
    {
        stage = (Stage) homePageInSettingsPage.getScene().getWindow();
        stage.close();
        User.postsIndexInHomePage = SQL.getHomePagePostsIDByID(User.myID).size()-1;
        if(User.postsIndexInHomePage!=-1)
        {
            User.commentIndexInHomePage = SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).size() - 1;
            if (User.commentIndexInHomePage != -1)
            {
                User.replyIndexInHomePage = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)).size() - 1;
            }
        }
        if(SQL.getBoolADByID(SQL.getUserIDByContentID(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage))) && User.postsIndexInHomePage!=-1)
        {
            SQL.postView(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage));
        }
        homePage();
    }
    @FXML
    public void fromSearchPageToHomePage() throws IOException,SQLException
    {
        stage = (Stage) userNameInSearchPage.getScene().getWindow();
        stage.close();
        User.postsIndexInHomePage = SQL.getHomePagePostsIDByID(User.myID).size()-1;
        if(User.postsIndexInHomePage!=-1)
        {
            User.commentIndexInHomePage = SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).size() - 1;
            if (User.commentIndexInHomePage != -1)
            {
                User.replyIndexInHomePage = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)).size() - 1;
            }
        }
        if(SQL.getBoolADByID(SQL.getUserIDByContentID(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage))) && User.postsIndexInHomePage!=-1)
        {
            SQL.postView(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage));
        }
        homePage();
    }
    @FXML
    public void fromCreateNewPostPageToHomePage() throws IOException,SQLException
    {
        stage = (Stage) messageInCreateNewPost.getScene().getWindow();
        stage.close();
        User.postsIndexInHomePage = SQL.getHomePagePostsIDByID(User.myID).size()-1;
        if(User.postsIndexInHomePage!=-1)
        {
            User.commentIndexInHomePage = SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).size() - 1;
            if (User.commentIndexInHomePage != -1)
            {
                User.replyIndexInHomePage = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)).size() - 1;
            }
        }
        if (User.postsIndexInHomePage!=-1)
            if(SQL.getBoolADByID(SQL.getUserIDByContentID(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage))) )
                SQL.postView(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage));

        homePage();
    }
    @FXML
    ImageView themeInHomePage;
    @FXML
    public void homePage() throws IOException
    {
        Parent nodes = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home page.fxml")));
        scene = new Scene(nodes);
        stage.getIcons().add(icon);
        stage.setTitle("Home page");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    ImageView profileOfPostInHomePage;
    @FXML
    Label userNameOfPostInHomePage;
    @FXML
    ImageView postInHomePage;
    @FXML
    Label captionInHomePage;
    @FXML
    ImageView likePostImageInHomePage;
    @FXML
    ImageView profileCommentInHomePage;
    @FXML
    Label commentInHomePage;
    @FXML
    ImageView likeCommentInHomePage;
    @FXML
    ImageView profileReplyInHomePage;
    @FXML
    Label replyInHomePage;
    @FXML
    TextField commentOrReplyInHomePage;
    @FXML
    public void setHomePage() throws SQLException
    {
        themeImage = new Image(User.theme);
        themeInHomePage.setImage(themeImage);
        if(SQL.getHomePagePostsIDByID(User.myID).size()!=0)
        {
            Image profileOfPost = new Image(SQL.getProfileImageByID(SQL.getUserIDByContentID(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage))));
            profileOfPostInHomePage.setImage(profileOfPost);
            userNameOfPostInHomePage.setText(SQL.getUserNameByID(SQL.getUserIDByContentID(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage))));
            Image post = new Image(SQL.getImageByContentID(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)));
            postInHomePage.setImage(post);
            if(SQL.getBoolADByID(SQL.getUserIDByContentID(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage))))
            {
                captionInHomePage.setText(SQL.getContentByContentID(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage))+" --AD Post!");
            }
            else
            {
                captionInHomePage.setText(SQL.getContentByContentID(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)));
            }
            if (SQL.liked(User.myID, SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)))
            {
                Image like = new Image("red like.png");
                likePostImageInHomePage.setImage(like);
            }
            else
            {
                Image like = new Image("white like.png");
                likePostImageInHomePage.setImage(like);
            }
            if(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).size()!=0)
            {
                Image profileComment = new Image(SQL.getProfileImageByID(SQL.getUserIDByContentID(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage))));
                profileCommentInHomePage.setImage(profileComment);
                commentInHomePage.setText(SQL.getUserNameByID(SQL.getUserIDByContentID(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage))) + " commented : " + SQL.getContentByContentID(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)) + " - " + SQL.likesNumber(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)) + " likes!");
                if (SQL.liked(User.myID, SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)))
                {
                    Image like = new Image("red like.png");
                    likeCommentInHomePage.setImage(like);
                }
                else
                {
                    Image like = new Image("white like.png");
                    likeCommentInHomePage.setImage(like);
                }
                if(SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)).size()!=0)
                {
                    Image profileReply = new Image(SQL.getProfileImageByID(SQL.getUserIDByContentID(SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)).get(User.replyIndexInHomePage))));
                    profileReplyInHomePage.setImage(profileReply);
                    replyInHomePage.setText(SQL.getUserNameByID(SQL.getUserIDByContentID(SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)).get(User.replyIndexInHomePage))) + " replied : " + SQL.getContentByContentID(SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)).get(User.replyIndexInHomePage)));
                }
            }
        }
    }
    @FXML
    public void likePostInHomePage() throws SQLException
    {
        if(SQL.liked(User.myID,SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)))
        {
            SQL.unlike(User.myID,SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage));
        }
        else
        {
            String Date;
            if(SQL.getBoolADByID(SQL.getUserIDByContentID(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage))))
            {
                Date = java.time.LocalDate.now().toString();
            }
            else
            {
                Date = "0";
            }
            SQL.like(User.myID,SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage),Date);
        }
        setHomePage();
    }
    @FXML
    public void nextPostInHomePage() throws SQLException
    {
        if(User.postsIndexInHomePage!=0)
        {
            User.postsIndexInHomePage--;
            if(SQL.getBoolADByID(SQL.getUserIDByContentID(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage))))
            {
                SQL.postView(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage));
            }
        }
        User.commentIndexInHomePage = SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).size()-1;
        if(User.commentIndexInHomePage!=-1)
        {
            User.replyIndexInHomePage = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)).size() - 1;
        }
        Image profile = new Image("default profile.jpg");
        profileCommentInHomePage.setImage(profile);
        profileReplyInHomePage.setImage(profile);
        commentInHomePage.setText("");
        replyInHomePage.setText("");
        setHomePage();
    }
    @FXML
    public void previousPostInHomePage() throws SQLException
    {
        if(User.postsIndexInHomePage!=SQL.getHomePagePostsIDByID(User.myID).size()-1)
        {
            User.postsIndexInHomePage++;
            if(SQL.getBoolADByID(SQL.getUserIDByContentID(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage))))
            {
                SQL.postView(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage));
            }
        }
        User.commentIndexInHomePage = SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).size()-1;
        if(User.commentIndexInHomePage!=-1)
        {
            User.replyIndexInHomePage = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)).size() - 1;
        }
        Image profile = new Image("default profile.jpg");
        profileCommentInHomePage.setImage(profile);
        profileReplyInHomePage.setImage(profile);
        commentInHomePage.setText("");
        replyInHomePage.setText("");
        setHomePage();
    }
    @FXML
    public void commentInHomePage() throws SQLException
    {
        SQL.comment(User.myID,SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage),commentOrReplyInHomePage.getCharacters().toString());
        User.commentIndexInHomePage++;
    }
    @FXML
    public void likeCommentInHomePage() throws SQLException
    {
        if(SQL.liked(User.myID,SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)))
        {
            SQL.unlike(User.myID,SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage));
        }
        else
        {
            SQL.like(User.myID,SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage),"0");
        }
        setHomePage();
    }
    @FXML
    public void nextCommentInHomePage() throws SQLException
    {
        if(User.commentIndexInHomePage!=0)
        {
            User.commentIndexInHomePage--;
        }
        User.replyIndexInHomePage = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)).size()-1;
        Image profile = new Image("default profile.jpg");
        profileReplyInHomePage.setImage(profile);
        replyInHomePage.setText("");
        setHomePage();
    }
    @FXML
    public void previousCommentInHomePage() throws SQLException
    {
        if(User.commentIndexInHomePage!=SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).size()-1)
        {
            User.commentIndexInHomePage++;
        }
        User.replyIndexInHomePage = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)).size()-1;
        Image profile = new Image("default profile.jpg");
        profileReplyInHomePage.setImage(profile);
        replyInHomePage.setText("");
        setHomePage();
    }
    @FXML
    public void replyInHomePage() throws SQLException
    {
        SQL.reply(User.myID,SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage),commentOrReplyInHomePage.getCharacters().toString());
        User.replyIndexInHomePage++;
    }
    @FXML
    public void nextReplyInHomePage() throws SQLException
    {
        if(User.replyIndexInHomePage!=0)
        {
            User.replyIndexInHomePage--;
        }
        setHomePage();
    }
    @FXML
    public void previousReplyInHomePage() throws SQLException
    {
        if(User.replyIndexInHomePage!=SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage)).get(User.commentIndexInHomePage)).size()-1)
        {
            User.replyIndexInHomePage++;
        }
        setHomePage();
    }
    @FXML
    Button myPageInHomePage;
    @FXML
    public void fromHomePageToMyPage() throws SQLException,IOException
    {
        stage = (Stage) myPageInHomePage.getScene().getWindow();
        stage.close();
        User.myPostsIndex = SQL.getPostsIDByUserID(User.myID).size()-1;
        if(User.myPostsIndex!=-1)
        {
            User.myCommentsIndex = SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).size() - 1;
            if(User.myCommentsIndex!=-1)
            {
                User.myRepliesIndex = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex)).size() - 1;
            }
        }
        myPage();
    }
    @FXML
    public void fromSettingsPageToMyPage() throws IOException,SQLException
    {
        stage = (Stage) homePageInSettingsPage.getScene().getWindow();
        stage.close();
        User.myPostsIndex = SQL.getPostsIDByUserID(User.myID).size()-1;
        if(User.myPostsIndex!=-1)
        {
            User.myCommentsIndex = SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).size() - 1;
            if(User.myCommentsIndex!=-1)
            {
                User.myRepliesIndex = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex)).size() - 1;
            }
        }
        myPage();
    }
    @FXML
    public void fromSearchPageToMyPage() throws IOException,SQLException
    {
        stage = (Stage) userNameInSearchPage.getScene().getWindow();
        stage.close();
        User.myPostsIndex = SQL.getPostsIDByUserID(User.myID).size()-1;
        if(User.myPostsIndex!=-1)
        {
            User.myCommentsIndex = SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).size() - 1;
            if(User.myCommentsIndex!=-1)
            {
                User.myRepliesIndex = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex)).size() - 1;
            }
        }
        myPage();
    }
    @FXML
    public void fromCreateNewPostToMyPage() throws IOException,SQLException
    {
        stage = (Stage) messageInCreateNewPost.getScene().getWindow();
        stage.close();
        User.myPostsIndex = SQL.getPostsIDByUserID(User.myID).size()-1;
        if(User.myPostsIndex!=-1)
        {
            User.myCommentsIndex = SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).size() - 1;
            if(User.myCommentsIndex!=-1)
            {
                User.myRepliesIndex = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex)).size() - 1;
            }
        }
        myPage();
    }
    public void myPage() throws IOException
    {
        Parent nodes = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("My page.fxml")));
        scene = new Scene(nodes);
        stage.getIcons().add(icon);
        stage.setTitle("My page");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    ImageView themeInMyPage;
    @FXML
    ImageView myProfileImage;
    @FXML
    Button userNameInMyPage;
    @FXML
    Button followersInMyPage;
    @FXML
    Button followingsInMyPage;
    @FXML
    ImageView postInMyPage;
    @FXML
    Label captionInMyPage;
    @FXML
    Button likesInMyPage;
    @FXML
    ImageView profileCommentInMyPage;
    @FXML
    Label commentInMyPage;
    @FXML
    ImageView likeCommentInMyPage;
    @FXML
    ImageView profileReplyInMyPage;
    @FXML
    Label replyInMyPage;
    @FXML
    TextField commentOrReplyInMyPage;
    @FXML
    Button profileViewInMyPage;
    @FXML
    Button viewsStatsInMyPage;
    @FXML
    Button likesStatsInMyPage;
    @FXML
    public void nextPostInMyPage() throws SQLException {
        if(User.myPostsIndex!=0)
        {
            User.myPostsIndex--;
        }
        Image profile = new Image("default profile.jpg");
        profileCommentInMyPage.setImage(profile);
        profileReplyInMyPage.setImage(profile);
        commentInMyPage.setText("");
        replyInMyPage.setText("");
        setMyPage();
    }
    @FXML
    public void previousPostInMyPage() throws SQLException {
        if(User.myPostsIndex!=SQL.getPostsIDByUserID(User.myID).size()-1)
        {
            User.myPostsIndex++;
        }
        Image profile = new Image("default profile.jpg");
        profileCommentInMyPage.setImage(profile);
        profileReplyInMyPage.setImage(profile);
        commentInMyPage.setText("");
        replyInMyPage.setText("");
        setMyPage();
    }
    @FXML
    public void removeInMyPage() throws SQLException {
        SQL.removePostByID(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex));
        if(User.myPostsIndex==SQL.getPostsIDByUserID(User.myID).size())
        {
            User.myPostsIndex--;
        }
        Image profile = new Image("default profile.jpg");
        profileCommentInMyPage.setImage(profile);
        profileReplyInMyPage.setImage(profile);
        commentInMyPage.setText("");
        replyInMyPage.setText("");
        setMyPage();
    }
    @FXML
    public void likeCommentInMyPage() throws SQLException
    {
        if(SQL.liked(User.myID,SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex)))
        {
            SQL.unlike(User.myID,SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex));
        }
        else
        {
            SQL.like(User.myID,SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex),"0");
        }
        setMyPage();
    }
    @FXML
    public void commentInMyPage() throws SQLException
    {
        SQL.comment(User.myID,SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex),commentOrReplyInMyPage.getCharacters().toString());
    }
    @FXML
    public void replyInMyPage() throws SQLException
    {
        SQL.reply(User.myID,SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex),commentOrReplyInMyPage.getCharacters().toString());
    }
    @FXML
    public void nextCommentInMyPage() throws SQLException
    {
        if(User.myCommentsIndex!=0)
        {
            User.myCommentsIndex--;
            User.myRepliesIndex = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex)).size()-1;
        }
        Image profile = new Image("default profile.jpg");
        profileReplyInMyPage.setImage(profile);
        replyInMyPage.setText("");
        setMyPage();
    }
    @FXML
    public void previousCommentInMyPage() throws SQLException
    {
        if(User.myCommentsIndex!=SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).size()-1)
        {
            User.myCommentsIndex++;
            User.myRepliesIndex = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex)).size()-1;
        }
        Image profile = new Image("default profile.jpg");
        profileReplyInMyPage.setImage(profile);
        replyInMyPage.setText("");
        setMyPage();
    }
    @FXML
    public void nextReplyInMyPage() throws SQLException
    {
        if(User.myRepliesIndex!=0)
        {
            User.myRepliesIndex--;
        }
        setMyPage();
    }
    @FXML
    public void previousReplyInMyPage() throws SQLException
    {
        if(User.myRepliesIndex!=SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex)).size()-1)
        {
            User.myRepliesIndex++;
        }
        setMyPage();
    }
    @FXML
    public void setMyPage() throws SQLException {
        themeImage = new Image(User.theme);
        themeInMyPage.setImage(themeImage);
        Image profile = new Image(User.profileImage);
        myProfileImage.setImage(profile);
        userNameInMyPage.setText(User.myUserName);
        followersInMyPage.setText("Followers : " + SQL.getFollowersUserNameByID(User.myID).size());
        followingsInMyPage.setText("Followings : " + SQL.getFollowingsIDByID(User.myID).size());
        if(SQL.getBoolADByID(User.myID))
        {
            profileViewInMyPage.setVisible(true);
            viewsStatsInMyPage.setVisible(true);
            likesStatsInMyPage.setVisible(true);
        }
        if (SQL.getPostsIDByUserID(User.myID).size() != 0)
        {
            Image postImage = new Image(SQL.getImageByContentID(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)));
            postInMyPage.setImage(postImage);
            captionInMyPage.setText(SQL.getContentByContentID(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)));
            likesInMyPage.setText(SQL.likesNumber(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)) + " likes!");
            if (SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).size() != 0)
            {
                Image profileComment = new Image(SQL.getProfileImageByID(SQL.getUserIDByContentID(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex))));
                profileCommentInMyPage.setImage(profileComment);
                commentInMyPage.setText(SQL.getUserNameByID(SQL.getUserIDByContentID(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex))) + " commented : " + SQL.getContentByContentID(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex)) + " - " + SQL.likesNumber(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex)) + " likes!");
                if (SQL.liked(User.myID, SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex)))
                {
                    Image like = new Image("red like.png");
                    likeCommentInMyPage.setImage(like);
                }
                else
                {
                    Image like = new Image("white like.png");
                    likeCommentInMyPage.setImage(like);
                }
                if (SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex)).size() != 0)
                {
                    Image profileReply = new Image(SQL.getProfileImageByID(SQL.getUserIDByContentID(SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex)).get(User.myRepliesIndex))));
                    profileReplyInMyPage.setImage(profileReply);
                    replyInMyPage.setText(SQL.getUserNameByID(SQL.getUserIDByContentID(SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex)).get(User.myRepliesIndex))) + " replied : " + SQL.getContentByContentID(SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex)).get(User.myCommentsIndex)).get(User.myRepliesIndex)));
                }
            }
        }
    }
    @FXML
    ImageView themeInSettings;
    @FXML
    CheckMenuItem lightTheme;
    @FXML
    CheckMenuItem darkTheme;
    @FXML
    public void setSettingsPage()
    {
        themeImage = new Image(User.theme);
        themeInSettings.setImage(themeImage);
    }
    @FXML
    public void lightTheme()
    {
        darkTheme.setSelected(false);
        User.theme = "light theme.jpg";
        themeImage = new Image(User.theme);
        themeInSettings.setImage(themeImage);
    }
    @FXML
    public void darkTheme()
    {
        lightTheme.setSelected(false);
        User.theme = "dark theme.jpg";
        themeImage = new Image(User.theme);
        themeInSettings.setImage(themeImage);
    }
    @FXML
    TextField profile;
    @FXML
    public void setProfile() throws SQLException
    {
        SQL.updateProfile(User.myID,profile.getCharacters().toString());
        User.profileImage = profile.getCharacters().toString();
    }
    @FXML
    public void fromHomePageToSettingsPage() throws IOException
    {
        stage = (Stage) myPageInHomePage.getScene().getWindow();
        stage.close();
        settingsPage();
    }
    @FXML
    public void fromMyPageToSettingsPage() throws IOException
    {
        stage = (Stage) homePageInMyPage.getScene().getWindow();
        stage.close();
        settingsPage();
    }
    @FXML
    public void fromSearchPageToSettingsPage() throws IOException
    {
        stage = (Stage) userNameInSearchPage.getScene().getWindow();
        stage.close();
        settingsPage();
    }
    @FXML
    public void fromCreateNewPostToSettingsPage() throws IOException
    {
        stage = (Stage) messageInCreateNewPost.getScene().getWindow();
        stage.close();
        settingsPage();
    }
    public void settingsPage() throws IOException
    {
        Parent nodes = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Settings page.fxml")));
        scene = new Scene(nodes);
        stage.getIcons().add(icon);
        stage.setTitle("Settings page");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    TextField searchedUserInHomePage;
    @FXML
    Label searchUserMessageInHomePage;
    @FXML
    public void searchUserInHomePage() throws IOException,SQLException
    {
        if(SQL.getIDByUserName(searchedUserInHomePage.getCharacters().toString())==-1)
        {
            searchUserMessageInHomePage.setTextFill(Color.RED);
            searchUserMessageInHomePage.setText("No Result!");
        }
        else
        {
            User.searchedUserName = searchedUserInHomePage.getCharacters().toString();
            User.searchedID = SQL.getIDByUserName(User.searchedUserName);
            stage = (Stage) searchUserMessageInHomePage.getScene().getWindow();
            stage.close();
            User.searchedUserPostsIndex = SQL.getPostsIDByUserID(User.searchedID).size()-1;
            if(User.searchedUserPostsIndex!=-1)
            {
                User.searchedUserCommentIndex = SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).size() - 1;
                if(User.searchedUserCommentIndex!=-1)
                {
                    User.searchedUserReplyIndex = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex)).size() - 1;
                }
            }
            if(SQL.getBoolADByID(User.searchedID))
            {
                SQL.profileView(User.searchedID,java.time.LocalDate.now().toString());
            }
            if(SQL.getBoolADByID(User.searchedID) && User.searchedUserPostsIndex!=-1)
            {
                SQL.postView(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex));
            }
            searchPage();
        }
    }
    @FXML
    TextField searchedUserInMyPage;
    @FXML
    Label searchUserMessageInMyPage;
    @FXML
    public void searchUserInMyPage() throws IOException,SQLException
    {
        if(SQL.getIDByUserName(searchedUserInMyPage.getCharacters().toString())==-1)
        {
            searchUserMessageInMyPage.setTextFill(Color.RED);
            searchUserMessageInMyPage.setText("No Result!");
        }
        else
        {
            User.searchedUserName = searchedUserInMyPage.getCharacters().toString();
            User.searchedID = SQL.getIDByUserName(User.searchedUserName);
            stage = (Stage) searchUserMessageInMyPage.getScene().getWindow();
            stage.close();
            User.searchedUserPostsIndex = SQL.getPostsIDByUserID(User.searchedID).size()-1;
            if(User.searchedUserPostsIndex!=-1)
            {
                User.searchedUserCommentIndex = SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).size() - 1;
                if(User.searchedUserCommentIndex!=-1)
                {
                    User.searchedUserReplyIndex = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex)).size() - 1;
                }
            }
            if(SQL.getBoolADByID(User.searchedID))
            {
                SQL.profileView(User.searchedID,java.time.LocalDate.now().toString());
            }
            if(SQL.getBoolADByID(User.searchedID) && User.searchedUserPostsIndex!=-1)
            {
                SQL.postView(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex));
            }
            searchPage();
        }
    }
    @FXML
    TextField searchedUserInSettingsPage;
    @FXML
    Label searchUserMessageInSettingsPage;
    @FXML
    public void searchUserInSettingsPage() throws IOException,SQLException
    {
        if(SQL.getIDByUserName(searchedUserInSettingsPage.getCharacters().toString())==-1)
        {
            searchUserMessageInSettingsPage.setTextFill(Color.RED);
            searchUserMessageInSettingsPage.setText("No Result!");
        }
        else
        {
            User.searchedUserName = searchedUserInSettingsPage.getCharacters().toString();
            User.searchedID = SQL.getIDByUserName(User.searchedUserName);
            stage = (Stage) searchUserMessageInSettingsPage.getScene().getWindow();
            stage.close();
            User.searchedUserPostsIndex = SQL.getPostsIDByUserID(User.searchedID).size()-1;
            if(User.searchedUserPostsIndex!=-1)
            {
                User.searchedUserCommentIndex = SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).size() - 1;
                if(User.searchedUserCommentIndex!=-1)
                {
                    User.searchedUserReplyIndex = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex)).size() - 1;
                }
            }
            if(SQL.getBoolADByID(User.searchedID))
            {
                SQL.profileView(User.searchedID,java.time.LocalDate.now().toString());
            }
            if(SQL.getBoolADByID(User.searchedID) && User.searchedUserPostsIndex!=-1)
            {
                SQL.postView(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex));
            }
            searchPage();
        }
    }
    @FXML
    TextField searchedUserInSearchPage;
    @FXML
    Label searchUserMessageInSearchPage;
    @FXML
    public void searchUserInSearchUserPage() throws IOException,SQLException
    {
        if(SQL.getIDByUserName(searchedUserInSearchPage.getCharacters().toString())==-1)
        {
            searchUserMessageInSearchPage.setTextFill(Color.RED);
            searchUserMessageInSearchPage.setText("No Result!");
        }
        else
        {
            User.searchedUserName = searchedUserInSearchPage.getCharacters().toString();
            User.searchedID = SQL.getIDByUserName(User.searchedUserName);
            stage = (Stage) searchUserMessageInSearchPage.getScene().getWindow();
            stage.close();
            User.searchedUserPostsIndex = SQL.getPostsIDByUserID(User.searchedID).size()-1;
            if(User.searchedUserPostsIndex!=-1)
            {
                User.searchedUserCommentIndex = SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).size() - 1;
                if(User.searchedUserCommentIndex!=-1)
                {
                    User.searchedUserReplyIndex = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex)).size() - 1;
                }
            }
            if(SQL.getBoolADByID(User.searchedID))
            {
                SQL.profileView(User.searchedID,java.time.LocalDate.now().toString());
            }
            if(SQL.getBoolADByID(User.searchedID) && User.searchedUserPostsIndex!=-1)
            {
                SQL.postView(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex));
            }
            searchPage();
        }
    }
    @FXML
    TextField searchedUserInCreateNewPostPage;
    @FXML
    Label searchUserMessageInCreateNewPostPage;
    @FXML
    public void searchUserInCreateNewPostPage() throws IOException,SQLException
    {
        if(SQL.getIDByUserName(searchedUserInCreateNewPostPage.getCharacters().toString())==-1)
        {
            searchUserMessageInCreateNewPostPage.setTextFill(Color.RED);
            searchUserMessageInCreateNewPostPage.setText("No Result!");
        }
        else
        {
            User.searchedUserName = searchedUserInCreateNewPostPage.getCharacters().toString();
            User.searchedID = SQL.getIDByUserName(User.searchedUserName);
            stage = (Stage) searchUserMessageInCreateNewPostPage.getScene().getWindow();
            stage.close();
            User.searchedUserPostsIndex = SQL.getPostsIDByUserID(User.searchedID).size()-1;
            if(User.searchedUserPostsIndex!=-1)
            {
                User.searchedUserCommentIndex = SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).size() - 1;
                if(User.searchedUserCommentIndex!=-1)
                {
                    User.searchedUserReplyIndex = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex)).size() - 1;
                }
            }
            if(SQL.getBoolADByID(User.searchedID))
            {
                SQL.profileView(User.searchedID,java.time.LocalDate.now().toString());
            }
            if(SQL.getBoolADByID(User.searchedID) && User.searchedUserPostsIndex!=-1)
            {
                SQL.postView(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex));
            }
            searchPage();
        }
    }
    @FXML
    public void searchPage() throws IOException
    {
        Parent nodes = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Search page.fxml")));
        scene = new Scene(nodes);
        stage.getIcons().add(icon);
        stage.setTitle("Search page");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    ImageView themeInSearchPage;
    @FXML
    ImageView userProfileInSearchPage;
    @FXML
    Button userNameInSearchPage;
    @FXML
    Button followersInSearchPage;
    @FXML
    Button followingsInSearchPage;
    @FXML
    Button followInSearchPage;
    @FXML
    ImageView postInSearchPage;
    @FXML
    Label contentInSearchPage;
    @FXML
    ImageView likePostInSearchPage;
    @FXML
    ImageView commentProfileInSearchPage;
    @FXML
    Label commentInSearchPage;
    @FXML
    ImageView likeCommentInSearchPage;
    @FXML
    ImageView replyProfileInSearchPage;
    @FXML
    Label replyInSearchPage;
    @FXML
    TextField commentOrReplyInSearchPage;
    @FXML
    public void setSearchPage() throws SQLException
    {
        themeImage = new Image(User.theme);
        themeInSearchPage.setImage(themeImage);
        Image searchedUserProfile = new Image(SQL.getProfileImageByID(User.searchedID));
        userProfileInSearchPage.setImage(searchedUserProfile);
        userNameInSearchPage.setText(User.searchedUserName);
        followersInSearchPage.setText("Followers : " + SQL.getFollowersUserNameByID(User.searchedID).size());
        followingsInSearchPage.setText("Followings : " + SQL.getFollowingsIDByID(User.searchedID).size());
        if(SQL.followed(User.myID,User.searchedID))
        {
            followInSearchPage.setText("Unfollow");
        }
        else
        {
            followInSearchPage.setText("Follow");
        }
        if(SQL.getPostsIDByUserID(User.searchedID).size()!=0)
        {
            Image searchUserPost = new Image(SQL.getImageByContentID(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)));
            postInSearchPage.setImage(searchUserPost);
            if(SQL.getBoolADByID(User.searchedID))
            {
                contentInSearchPage.setText(SQL.getContentByContentID(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex))+" --AD Post!");
            }
            else
            {
                contentInSearchPage.setText(SQL.getContentByContentID(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)));
            }
            if (SQL.liked(User.myID, SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)))
            {
                Image like = new Image("red like.png");
                likePostInSearchPage.setImage(like);
            }
            else
            {
                Image like = new Image("white like.png");
                likePostInSearchPage.setImage(like);
            }
            if(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).size()!=0)
            {
                Image profileComment = new Image(SQL.getProfileImageByID(SQL.getUserIDByContentID(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex))));
                commentProfileInSearchPage.setImage(profileComment);
                commentInSearchPage.setText(SQL.getUserNameByID(SQL.getUserIDByContentID(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex)))+" commented : "+SQL.getContentByContentID(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex))+" - "+SQL.likesNumber(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex))+" likes!");
                if(SQL.liked(User.myID,SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex)))
                {
                    Image like = new Image("red like.png");
                    likeCommentInSearchPage.setImage(like);
                }
                else
                {
                    Image like = new Image("white like.png");
                    likeCommentInSearchPage.setImage(like);
                }
                if(SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex)).size()!=0)
                {
                    Image profileReply = new Image(SQL.getProfileImageByID(SQL.getUserIDByContentID(SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex)).get(User.searchedUserReplyIndex))));
                    replyProfileInSearchPage.setImage(profileReply);
                    replyInSearchPage.setText(SQL.getUserNameByID(SQL.getUserIDByContentID(SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex)).get(User.searchedUserReplyIndex)))+" replied : "+SQL.getContentByContentID(SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex)).get(User.searchedUserReplyIndex)));

                }
            }
        }
    }
    @FXML
    public void likePostInSearchPage() throws SQLException
    {
        if(SQL.liked(User.myID,SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)))
        {
            SQL.unlike(User.myID,SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex));
        }
        else
        {
            String date = "0";
            if(SQL.getBoolADByID(User.searchedID))
            {
                date = java.time.LocalDate.now().toString();
            }
            SQL.like(User.myID,SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex),date);
        }
        setSearchPage();
    }
    @FXML
    public void followInSearchPage() throws SQLException
    {
        if(SQL.followed(User.myID,User.searchedID))
        {
            SQL.unfollow(User.myID,User.searchedID);
        }
        else
        {
            SQL.follow(User.myID,User.searchedID);
        }
        setSearchPage();
    }
    @FXML
    public void nextPostInSearchPage() throws SQLException
    {
        if(User.searchedUserPostsIndex!=0)
        {
            User.searchedUserPostsIndex--;
            User.searchedUserCommentIndex = SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).size()-1;
            if(User.searchedUserCommentIndex!=-1)
            {
                User.searchedUserReplyIndex = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex)).size() - 1;
            }
            if(SQL.getBoolADByID(User.searchedID))
            {
                SQL.postView(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex));
            }
        }
        Image profile = new Image("default profile.jpg");
        commentProfileInSearchPage.setImage(profile);
        replyProfileInSearchPage.setImage(profile);
        commentInSearchPage.setText("");
        replyInSearchPage.setText("");
        setSearchPage();
    }
    @FXML
    public void previousPostInSearchPage() throws SQLException
    {
        if(User.searchedUserPostsIndex!=SQL.getPostsIDByUserID(User.searchedID).size()-1)
        {
            User.searchedUserPostsIndex++;
            User.searchedUserCommentIndex = SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).size()-1;
            if(User.searchedUserCommentIndex!=-1)
            {
                User.searchedUserReplyIndex = SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex)).size() - 1;
            }
            if(SQL.getBoolADByID(User.searchedID))
            {
                SQL.postView(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex));
            }
        }
        Image profile = new Image("default profile.jpg");
        commentProfileInSearchPage.setImage(profile);
        replyProfileInSearchPage.setImage(profile);
        commentInSearchPage.setText("");
        replyInSearchPage.setText("");
        setSearchPage();
    }
    @FXML
    public void likeCommentInSearchPage() throws SQLException
    {
        if(SQL.liked(User.myID,SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex)))
        {
            SQL.unlike(User.myID,SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex));
        }
        else
        {
            SQL.like(User.myID,SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex),"0");
        }
        setSearchPage();
    }
    @FXML
    public void commentInSearchPage() throws SQLException
    {
        SQL.comment(User.myID,SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex),commentOrReplyInSearchPage.getCharacters().toString());
    }
    @FXML
    public void nextCommentInSearchPage() throws SQLException
    {
        if(User.searchedUserCommentIndex!=0)
        {
            User.searchedUserCommentIndex--;
            Image profile = new Image("default profile.jpg");
            replyProfileInSearchPage.setImage(profile);
            replyInSearchPage.setText("");
        }
        setSearchPage();
    }
    @FXML
    public void previousCommentInSearchPage() throws SQLException
    {
        if(User.searchedUserCommentIndex!=SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).size()-1)
        {
            User.searchedUserCommentIndex++;
            Image profile = new Image("default profile.jpg");
            replyProfileInSearchPage.setImage(profile);
            replyInSearchPage.setText("");
        }
        setSearchPage();
    }
    @FXML
    public void replyInSearchPage() throws SQLException
    {
        SQL.reply(User.myID,SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex),commentOrReplyInSearchPage.getCharacters().toString());
    }
    @FXML
    public void nextReplyInSearchPage() throws SQLException
    {
        if(User.searchedUserReplyIndex!=0)
        {
            User.searchedUserReplyIndex--;
        }
        setSearchPage();
    }
    @FXML
    public void previousReplyInSearchPage() throws SQLException
    {
        if(User.searchedUserReplyIndex!=SQL.getRepliesIDOfComment(SQL.getCommentsIDOfPost(SQL.getPostsIDByUserID(User.searchedID).get(User.searchedUserPostsIndex)).get(User.searchedUserCommentIndex)).size()-1)
        {
            User.searchedUserReplyIndex++;
        }
        setSearchPage();
    }
    @FXML
    ImageView postImageInCreateNewPost;
    @FXML
    TextField postImageNameInCreateNewPost;
    @FXML
    public void setPostImageInCreateNewPost()
    {
        Image postImage = new Image(postImageNameInCreateNewPost.getCharacters().toString());
        postImageInCreateNewPost.setImage(postImage);
    }
    @FXML
    TextField captionInCreateNewPost;
    @FXML
    Label messageInCreateNewPost;
    @FXML
    public void postInCreateNewPost() throws SQLException
    {
        SQL.createNewPost(User.myID,captionInCreateNewPost.getCharacters().toString(),User.businessAccount,postImageNameInCreateNewPost.getCharacters().toString());
        messageInCreateNewPost.setTextFill(Color.GREEN);
        messageInCreateNewPost.setText("Posted!");
    }
    @FXML
    ImageView themeInCreateNewPostPage;
    @FXML
    public void setCreateNewPostPage()
    {
        Image theme = new Image(User.theme);
        themeInCreateNewPostPage.setImage(theme);
    }
    @FXML
    public void fromHomePageToCreateNewPost() throws IOException
    {
        stage = (Stage) searchUserMessageInHomePage.getScene().getWindow();
        stage.close();
        createNewPostPage();
    }
    @FXML
    public void fromMyPageToCreateNewPost() throws IOException
    {
        stage = (Stage) searchUserMessageInMyPage.getScene().getWindow();
        stage.close();
        createNewPostPage();
    }
    @FXML
    public void fromSearchPageToCreateNewPost() throws IOException
    {
        stage = (Stage) searchUserMessageInSearchPage.getScene().getWindow();
        stage.close();
        createNewPostPage();
    }
    @FXML
    public void fromSettingsPageToCreateNewPost() throws IOException
    {
        stage = (Stage) searchUserMessageInSettingsPage.getScene().getWindow();
        stage.close();
        createNewPostPage();
    }
    public void createNewPostPage() throws IOException
    {
        Parent nodes = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Create new post page.fxml")));
        scene = new Scene(nodes);
        stage.getIcons().add(icon);
        stage.setTitle("Create new post page");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    ImageView themeInStatsPage;
    @FXML
    Label stats1;
    @FXML
    Label stats2;
    @FXML
    Label stats3;
    @FXML
    Label stats4;
    @FXML
    Label stats5;
    @FXML
    Label stats6;
    @FXML
    public void profileViews() throws IOException
    {
        User.profileStats = true;
        User.postViewsStats = false;
        User.postLikesStats = false;
        statsPage();
    }
    @FXML
    public void postViews() throws IOException
    {
        User.profileStats = false;
        User.postViewsStats = true;
        User.postLikesStats = false;
        statsPage();
    }
    @FXML
    public void postLikes() throws IOException
    {
        User.profileStats = false;
        User.postViewsStats = false;
        User.postLikesStats = true;
        statsPage();
    }
    public void statsPage() throws IOException
    {
        Parent nodes = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Stats page.fxml")));
        scene = new Scene(nodes);
        stage.getIcons().add(icon);
        stage.setTitle("Stats page");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void setStatsPage() throws SQLException
    {
        Image theme = new Image(User.theme);
        themeInStatsPage.setImage(theme);
        if(User.profileStats)
        {
            ArrayList<String> profileStats = SQL.getProfileViewsByID(User.myID);
            if(profileStats.size()>0)
            {
                stats1.setText(profileStats.get(0));
            }
            if(profileStats.size()>1)
            {
                stats2.setText(profileStats.get(1));
            }
            if(profileStats.size()>2)
            {
                stats3.setText(profileStats.get(2));
            }
            if(profileStats.size()>3)
            {
                stats4.setText(profileStats.get(3));
            }
            if(profileStats.size()>4)
            {
                stats5.setText(profileStats.get(4));
            }
            if(profileStats.size()>5)
            {
                stats6.setText(profileStats.get(5));
            }
        }
        if(User.postLikesStats)
        {
            ArrayList<String> postLikesStats = SQL.getLikesStats(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex));
            if(postLikesStats.size()>0)
            {
                stats1.setText(postLikesStats.get(0));
            }
            if(postLikesStats.size()>1)
            {
                stats2.setText(postLikesStats.get(1));
            }
            if(postLikesStats.size()>2)
            {
                stats3.setText(postLikesStats.get(2));
            }
            if(postLikesStats.size()>3)
            {
                stats4.setText(postLikesStats.get(3));
            }
            if(postLikesStats.size()>4)
            {
                stats5.setText(postLikesStats.get(4));
            }
            if(postLikesStats.size()>5)
            {
                stats6.setText(postLikesStats.get(5));
            }
        }
        if(User.postViewsStats)
        {
            ArrayList<String> postViews = SQL.getPostViews(SQL.getPostsIDByUserID(User.myID).get(User.myPostsIndex));
            if(postViews.size()>0)
            {
                stats1.setText(postViews.get(0));
            }
            if(postViews.size()>1)
            {
                stats2.setText(postViews.get(1));
            }
            if(postViews.size()>2)
            {
                stats3.setText(postViews.get(2));
            }
            if(postViews.size()>3)
            {
                stats4.setText(postViews.get(3));
            }
            if(postViews.size()>4)
            {
                stats5.setText(postViews.get(4));
            }
            if(postViews.size()>5)
            {
                stats6.setText(postViews.get(5));
            }
        }
    }
    @FXML
    ImageView suggestedUserProfile1;
    @FXML
    ImageView suggestedUserProfile2;
    @FXML
    ImageView suggestedUserProfile3;
    @FXML
    ImageView suggestedUserProfile4;
    @FXML
    ImageView suggestedUserProfile5;
    @FXML
    ImageView suggestedUserProfile6;
    @FXML
    Label suggestedUserName1;
    @FXML
    Label suggestedUserName2;
    @FXML
    Label suggestedUserName3;
    @FXML
    Label suggestedUserName4;
    @FXML
    Label suggestedUserName5;
    @FXML
    Label suggestedUserName6;
    @FXML
    public void suggestedUsersPage() throws IOException
    {
        Parent nodes = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Suggested users page.fxml")));
        scene = new Scene(nodes);
        stage.getIcons().add(icon);
        stage.setTitle("Suggested users page");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void setSuggestedUsersPage() throws SQLException
    {
        ArrayList<Integer> userIDs = SQL.getSuggestedUserIDsByUserID(User.myID);
        if(userIDs.size()>0)
        {
            Image profile = new Image(SQL.getProfileImageByID(userIDs.get(0)));
            suggestedUserProfile1.setImage(profile);
            suggestedUserName1.setText(SQL.getUserNameByID(userIDs.get(0)));
        }
        if(userIDs.size()>1)
        {
            Image profile = new Image(SQL.getProfileImageByID(userIDs.get(1)));
            suggestedUserProfile2.setImage(profile);
            suggestedUserName2.setText(SQL.getUserNameByID(userIDs.get(1)));
        }
        if(userIDs.size()>2)
        {
            Image profile = new Image(SQL.getProfileImageByID(userIDs.get(2)));
            suggestedUserProfile3.setImage(profile);
            suggestedUserName3.setText(SQL.getUserNameByID(userIDs.get(2)));
        }
        if(userIDs.size()>3)
        {
            Image profile = new Image(SQL.getProfileImageByID(userIDs.get(3)));
            suggestedUserProfile4.setImage(profile);
            suggestedUserName4.setText(SQL.getUserNameByID(userIDs.get(3)));
        }
        if(userIDs.size()>4)
        {
            Image profile = new Image(SQL.getProfileImageByID(userIDs.get(4)));
            suggestedUserProfile5.setImage(profile);
            suggestedUserName5.setText(SQL.getUserNameByID(userIDs.get(4)));
        }
        if(userIDs.size()>5)
        {
            Image profile = new Image(SQL.getProfileImageByID(userIDs.get(5)));
            suggestedUserProfile6.setImage(profile);
            suggestedUserName6.setText(SQL.getUserNameByID(userIDs.get(5)));
        }
    }
    @FXML
    public void logoutFromHomePage() throws IOException
    {
        stage = (Stage) myPageInHomePage.getScene().getWindow();
        stage.close();
        loginPage();
    }
    @FXML
    public void logoutFromMyPage() throws IOException
    {
        stage = (Stage) searchUserMessageInMyPage.getScene().getWindow();
        stage.close();
        loginPage();
    }
    @FXML
    public void logoutFromSettingsPage() throws IOException
    {
        stage = (Stage) searchUserMessageInSettingsPage.getScene().getWindow();
        stage.close();
        loginPage();
    }
    @FXML
    public void logoutFromSearchPage() throws IOException
    {
        stage = (Stage) searchUserMessageInSearchPage.getScene().getWindow();
        stage.close();
        loginPage();
    }
    @FXML
    public void logoutFromCreateNewPostPage() throws IOException
    {
        stage = (Stage) searchUserMessageInCreateNewPostPage.getScene().getWindow();
        stage.close();
        loginPage();
    }
    public void loginPage() throws IOException
    {
        Parent nodes = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login page.fxml")));
        scene = new Scene(nodes);
        stage.getIcons().add(icon);
        stage.setTitle("Login page");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void passwordRecovery1() throws IOException
    {
        Parent nodes = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Password recovery page1.fxml")));
        scene = new Scene(nodes);
        stage.getIcons().add(icon);
        stage.setTitle("Password Recovery page");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    Label messageInRecoveryPage1;
    @FXML
    TextField userNameInRecoveryPage;
    @FXML
    public void passwordRecovery2() throws IOException,SQLException
    {
        if(Login.showSecurityQuestion(userNameInRecoveryPage.getCharacters().toString())==-1)
        {
            messageInRecoveryPage1.setTextFill(Color.RED);
            messageInRecoveryPage1.setText("User name doesn't exist!");
        }
        else
        {
            User.myUserName=userNameInRecoveryPage.getCharacters().toString();
            stage = (Stage) messageInRecoveryPage1.getScene().getWindow();
            stage.close();
            Parent nodes = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Password recovery page2.fxml")));
            scene = new Scene(nodes);
            stage.getIcons().add(icon);
            stage.setTitle("Password Recovery page");
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    Label SQ;
    @FXML
    PasswordField SQAnswer;
    @FXML
    public void passwordRecovery3() throws SQLException
    {
        if(Login.showSecurityQuestion(User.myUserName)==1)
        {
            SQ.setText("Which city were you born in?");
        }
        if(Login.showSecurityQuestion(User.myUserName)==2)
        {
            SQ.setText("What is your oldest sibling’s first name?");
        }
        if(Login.showSecurityQuestion(User.myUserName)==3)
        {
            SQ.setText("What is the name of your first school?");
        }
        if(Login.showSecurityQuestion(User.myUserName)==4)
        {
            SQ.setText("What were the last four digits of your childhood telephone number?");
        }
    }
    @FXML
    Label messageInRecoveryPage2;
    @FXML
    public void passwordRecovery4() throws IOException,SQLException
    {
        if(!Login.checkAnswer(User.myUserName,SQAnswer.getCharacters().toString()))
        {
            messageInRecoveryPage2.setTextFill(Color.RED);
            messageInRecoveryPage2.setText("Wrong answer!");
        }
        else
        {
            stage = (Stage) messageInRecoveryPage2.getScene().getWindow();
            stage.close();
            Parent nodes = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Password recovery page3.fxml")));
            scene = new Scene(nodes);
            stage.getIcons().add(icon);
            stage.setTitle("Password Recovery page");
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    PasswordField newPassword1;
    @FXML
    PasswordField newPassword2;
    @FXML
    Label messageInRecoveryPage3;
    @FXML
    public void passwordRecovery5() throws SQLException
    {
        if(!newPassword1.getCharacters().toString().equals(newPassword2.getCharacters().toString()))
        {
            messageInRecoveryPage3.setTextFill(Color.RED);
            messageInRecoveryPage3.setText("The passwords aren't same!");
            return;
        }
        if(Login.changePasswordByUserName(User.myUserName,newPassword1.getCharacters().toString()).equals("Password changed successfully!"))
        {
            messageInRecoveryPage3.setTextFill(Color.GREEN);
            messageInRecoveryPage3.setText("Password changed successfully!");
        }
        else
        {
            messageInRecoveryPage3.setTextFill(Color.RED);
            messageInRecoveryPage3.setText(Login.changePasswordByUserName(User.myUserName,newPassword1.getCharacters().toString()));
        }
    }
    @FXML
    public void signup1() throws IOException
    {
        Parent nodes = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Signup page.fxml")));
        scene = new Scene(nodes);
        stage.getIcons().add(icon);
        stage.setTitle("Signup page");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    CheckMenuItem SQN1;
    @FXML
    CheckMenuItem SQN2;
    @FXML
    CheckMenuItem SQN3;
    @FXML
    CheckMenuItem SQN4;
    @FXML
    public void SQN1()
    {
        User.SQNumber=1;
        SQN2.setSelected(false);
        SQN3.setSelected(false);
        SQN4.setSelected(false);
    }
    @FXML
    public void SQN2()
    {
        User.SQNumber=2;
        SQN1.setSelected(false);
        SQN3.setSelected(false);
        SQN4.setSelected(false);
    }
    @FXML
    public void SQN3()
    {
        User.SQNumber=3;
        SQN1.setSelected(false);
        SQN2.setSelected(false);
        SQN4.setSelected(false);
    }
    @FXML
    public void SQN4()
    {
        User.SQNumber=4;
        SQN1.setSelected(false);
        SQN2.setSelected(false);
        SQN3.setSelected(false);
    }
    @FXML
    CheckMenuItem personalAccount;
    @FXML
    CheckMenuItem businessAccount;
    @FXML
    public void personalAccount()
    {
        User.businessAccount=false;
        businessAccount.setSelected(false);
    }
    @FXML
    public void businessAccount()
    {
        User.businessAccount=true;
        personalAccount.setSelected(false);
    }
    @FXML
    TextField userNameInSignupPage;
    @FXML
    PasswordField password1InSignupPage;
    @FXML
    PasswordField password2InSignupPage;
    @FXML
    PasswordField SQAnswerInSignupPage;
    @FXML
    Label messageInSignupPage;
    @FXML
    public void signup() throws SQLException
    {
        String accountType;
        if(User.businessAccount)
        {
            accountType = "business";
        }
        else
        {
            accountType = "personal";
        }
        String message = SignUp.signup(userNameInSignupPage.getCharacters().toString(),password1InSignupPage.getCharacters().toString(),
                password2InSignupPage.getCharacters().toString(),User.SQNumber,SQAnswerInSignupPage.getCharacters().toString(),accountType);
        if (!message.equals("Signed up successfully!"))
        {
            messageInSignupPage.setTextFill(Color.RED);
        }
        else
        {
            messageInSignupPage.setTextFill(Color.GREEN);
        }
        messageInSignupPage.setText(message);
    }
}