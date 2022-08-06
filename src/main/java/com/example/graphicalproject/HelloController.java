package com.example.graphicalproject;

import controller.Login;
import controller.SignUp;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import controller.User;
import javafx.stage.Window;
import model.SQL;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class HelloController {

    Stage stage = new Stage();
    Scene scene;
    Image icon = new Image("logo.png");
    @FXML
    TextField searchedUserInChat;
    @FXML
    Label searchUserMessageInChat;
    @FXML
    ImageView themeInChat;
    Image themeImage;
    @FXML
    TextField userNameInLoginPage;
    @FXML
    PasswordField passwordInLoginPage;
    @FXML
    Text userDoesntFoundText;
    @FXML
    Text wrongPasswordText;
    public void login() throws SQLException,IOException
    {
        String loginCondition = controller.Login.login(userNameInLoginPage.getText(),passwordInLoginPage.getText());
        if(loginCondition.equals("Password is wrong!")) {
            wrongPasswordText.setVisible(true);
            userDoesntFoundText.setVisible(false);
        }
        else if (loginCondition.equals("Username doesn't exist!")) {
            userDoesntFoundText.setVisible(true);
            wrongPasswordText.setVisible(false);
        }
        else
        {
            wrongPasswordText.setVisible(false);
            userDoesntFoundText.setVisible(false);
            stage = (Stage) wrongPasswordText.getScene().getWindow();
            stage.close();
            User.myUserName = userNameInLoginPage.getText();
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
        if ( User.postsIndexInHomePage!=-1)
            if(SQL.getBoolADByID(SQL.getUserIDByContentID(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage))))
                SQL.postView(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage));
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
        if (User.postsIndexInHomePage!=-1)
            if(SQL.getBoolADByID(SQL.getUserIDByContentID(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage))))
                SQL.postView(SQL.getHomePagePostsIDByID(User.myID).get(User.postsIndexInHomePage));
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
    Label myUserNameInSettingLabel;
    @FXML
    ImageView myProfileInSettingImageView;
    @FXML
    public void setSettingsPage()
    {
        themeImage = new Image(User.theme);
        themeInSettings.setImage(themeImage);
        myUserNameInSettingLabel.setText(User.myUserName);
        myProfileInSettingImageView.setImage(new Image(User.profileImage));
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
    public void setProfile() throws SQLException
    {
        String path = chooseFile(searchedUserInSettingsPage.getScene().getWindow());
        if (path != null) {
            SQL.updateProfile(User.myID,path);
            User.profileImage = path;
            myProfileInSettingImageView.setImage(new Image(User.profileImage));
        }
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
    @FXML
    public void fromChatToSettingPage () throws IOException {
        stage = (Stage) searchedUserInChat.getScene().getWindow();
        stage.close();
        settingsPage();
    }
    public void settingsPage() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings page.fxml"));
        Parent root = loader.load();
        HelloController controller = loader.getController();
        controller.setSettingsPage();
        scene = new Scene(root);
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
    public void setPostImageInCreateNewPost()
    {
        String path = chooseFile(searchedUserInCreateNewPostPage.getScene().getWindow());
        if (path != null) {
            Image postImage = new Image(path);
            postImageInCreateNewPost.setImage(postImage);
        }
    }
    @FXML
    TextField captionInCreateNewPost;
    @FXML
    Label messageInCreateNewPost;
    @FXML
    public void postInCreateNewPost() throws SQLException
    {
        SQL.createNewPost(User.myID,captionInCreateNewPost.getText(),User.businessAccount,postImageInCreateNewPost.getImage().getUrl());
        System.out.println(postImageInCreateNewPost.getImage().getUrl());
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

    public void logoutFromChat () throws IOException {
        stage = (Stage) searchedUserInChat.getScene().getWindow();
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
    public void passwordRecovery1(MouseEvent event) throws IOException {
        Parent nodes = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Password Recovery.fxml")));
        scene = new Scene(nodes);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.getIcons().add(icon);
        stage.setTitle("Password Recovery");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    TextField passwordRecoveryTextField;
    @FXML
    Text doesntExistPasswordRecovery;
    @FXML
    Label passwordRecoveryLabel;
    @FXML
    Button checkSQAnswerButton;
    @FXML
    Pane checkSQPane;
    @FXML
    Pane setNewPassPane;
    @FXML
    Text passWordErrorPassRecovery;
    @FXML
    Text passNotSameRecovery;
    @FXML
    Text changedSuccessfully;
    @FXML
    PasswordField newPassword;
    @FXML
    PasswordField newPasswordRepeat;

    public void checkUserNamePasswordRecovery() throws SQLException {
        int SQNumber = Login.showSecurityQuestion(passwordRecoveryTextField.getText());
        if(SQNumber == -1) {
            doesntExistPasswordRecovery.setVisible(true);
            doesntExistPasswordRecovery.setText("UserName Doesn't Exist");
        }
        else {
            doesntExistPasswordRecovery.setVisible(false);
            User.myUserName=passwordRecoveryTextField.getCharacters().toString();
            String[] SQString = {"Which city were you born in?","What is your oldest sibling’s first name?","What is the name of your first school?","What were the last four digits of your childhood telephone number?"};
            passwordRecoveryLabel.setText(SQString[SQNumber-1]);
            passwordRecoveryTextField.clear();
            checkSQAnswerButton.setVisible(true);
        }
    }

    public void checkSQAnswer () throws SQLException {
        if(!Login.checkAnswer(User.myUserName,passwordRecoveryTextField.getText())) {
            doesntExistPasswordRecovery.setVisible(true);
            doesntExistPasswordRecovery.setText("Wrong answer!");
            passwordRecoveryLabel.setText("Enter Your UserName");
            passwordRecoveryTextField.clear();
            checkSQAnswerButton.setVisible(false);
        } else {
            doesntExistPasswordRecovery.setVisible(false);
            passwordRecoveryLabel.setText("Enter Your UserName");
            passwordRecoveryTextField.clear();
            checkSQAnswerButton.setVisible(false);
            checkSQPane.setVisible(false);
            setNewPassPane.setVisible(true);
        }

    }

    public void changePassword() throws SQLException
    {
        if(!newPassword.getText().equals(newPasswordRepeat.getText())) {
             passWordErrorPassRecovery.setVisible(false);
             passNotSameRecovery.setVisible(true);
             changedSuccessfully.setVisible(false);
             return;
        }
        String message = Login.changePasswordByUserName(User.myUserName,newPassword.getCharacters().toString());
        if(message.equals("Password changed successfully!")) {
            passWordErrorPassRecovery.setVisible(false);
            passNotSameRecovery.setVisible(false);
            changedSuccessfully.setVisible(true);
            newPassword.clear();
            newPasswordRepeat.clear();
            checkSQPane.setVisible(true);
            setNewPassPane.setVisible(false);
        }
        else {
            passWordErrorPassRecovery.setText(message);
            passWordErrorPassRecovery.setVisible(true);
            passNotSameRecovery.setVisible(false);
            changedSuccessfully.setVisible(false);
        }
    }
    @FXML
    public void signup1(ActionEvent event) throws IOException
    {
        Parent nodes = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Signup page.fxml")));
        stage =((Stage)((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(nodes);
        stage.getIcons().add(icon);
        stage.setTitle("Signup page");
        stage.setScene(scene);
    }
    @FXML
    MenuButton securityMenuButton;
    @FXML
    CheckBox personalCheckBox;
    @FXML
    CheckBox businessCheckBox;
    @FXML
    TextField userNameInSignupPage;
    @FXML
    PasswordField password1InSignupPage;
    @FXML
    PasswordField password2InSignupPage;
    @FXML
    TextField SQAnswerInSignupPage;
    @FXML
    Text passwordAreNotSaneText;
    @FXML
    Text passWordErrorText;
    @FXML
    Text userNameExistText;
    @FXML
    Text signUpSuccessfullyText;

    public void changeSceneToLogin (ActionEvent event) throws IOException {
        Parent nodes = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login page.fxml")));
        stage =((Stage)((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(nodes);
        stage.getIcons().add(icon);
        stage.setTitle("login page");
        stage.setScene(scene);
    }

    public void setMenuButtonText (ActionEvent mouseEvent) {
        securityMenuButton.setText(((MenuItem)mouseEvent.getSource()).getText());
        securityMenuButton.setId(((MenuItem)mouseEvent.getSource()).getId());
    }

    public void setChooseAccountType (MouseEvent mouseEvent) {
        boolean isPersonalCheckBos = Objects.equals(mouseEvent.getSource(),personalCheckBox);
        if (isPersonalCheckBos) {
            businessCheckBox.setSelected(!personalCheckBox.isSelected());
        } else {
            personalCheckBox.setSelected(!businessCheckBox.isSelected());
        }
    }

    public void signup() throws SQLException {
        User.businessAccount = businessCheckBox.isSelected();
        User.SQNumber = Integer.parseInt(securityMenuButton.getId());
        String accountType;
        if(User.businessAccount)
            accountType = "business";
        else
            accountType = "personal";

        String message = SignUp.signup(userNameInSignupPage.getText(),password1InSignupPage.getText(),password2InSignupPage.getText(),User.SQNumber,SQAnswerInSignupPage.getText(),accountType);
        switch (message) {
            case "Username already exists!" -> {
                userNameExistText.setVisible(true);
                passWordErrorText.setVisible(false);
                passwordAreNotSaneText.setVisible(false);
                signUpSuccessfullyText.setVisible(false);
            }
            case "Passwords are not same!" -> {
                userNameExistText.setVisible(false);
                passWordErrorText.setVisible(false);
                passwordAreNotSaneText.setVisible(true);
                signUpSuccessfullyText.setVisible(false);
            }
            case "Signed up successfully!" -> {
                userNameExistText.setVisible(false);
                passWordErrorText.setVisible(false);
                passwordAreNotSaneText.setVisible(false);
                signUpSuccessfullyText.setVisible(true);
            }
            default -> {
                userNameExistText.setVisible(false);
                passWordErrorText.setVisible(true);
                passWordErrorText.setText(message);
                passwordAreNotSaneText.setVisible(false);
                signUpSuccessfullyText.setVisible(false);
            }
        }
    }

    public void fromSearchPageToChat () throws IOException, SQLException {
        stage = (Stage) userNameInSearchPage.getScene().getWindow();
        stage.close();
        showChatPage();
    }

    public void fromCreateNewPostToChat () throws IOException, SQLException {
        stage = (Stage) messageInCreateNewPost.getScene().getWindow();
        stage.close();
        showChatPage();
    }

    public void fromHomePageToChat () throws IOException, SQLException {
        stage = (Stage) myPageInHomePage.getScene().getWindow();
        stage.close();
        showChatPage();
    }

    public void fromMyPageToChat () throws IOException, SQLException {
        stage = (Stage) homePageInMyPage.getScene().getWindow();
        stage.close();
        showChatPage();
    }

    public void fromSettingPageToChat () throws IOException, SQLException {
        stage = (Stage) homePageInSettingsPage.getScene().getWindow();
        stage.close();
        showChatPage();
    }

    public void fromChatToHomePage() throws IOException,SQLException
    {
        stage = (Stage) searchUserMessageInChat.getScene().getWindow();
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

    public void fromChatToMyPage() throws SQLException,IOException
    {
        stage = (Stage) searchUserMessageInChat.getScene().getWindow();
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

    public void fromChatToCreateNewPost() throws IOException
    {
        stage = (Stage) searchUserMessageInChat.getScene().getWindow();
        stage.close();
        createNewPostPage();
    }

    public void searchUserInChat() throws IOException,SQLException
    {
        if(SQL.getIDByUserName(searchedUserInChat.getCharacters().toString())==-1)
        {
            searchUserMessageInChat.setTextFill(Color.RED);
            searchUserMessageInChat.setText("No Result!");
        }
        else
        {
            User.searchedUserName = searchedUserInChat.getCharacters().toString();
            User.searchedID = SQL.getIDByUserName(User.searchedUserName);
            stage = (Stage) searchUserMessageInChat.getScene().getWindow();
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
    TableView<HelloController.Test> chatListTable;
    @FXML
    ListView<Object> chatPageListView;
    @FXML
    ImageView chatProfileImageView;
    @FXML
    Label chatNameLabel;
    @FXML
    Pane myMessageDetailPane;
    @FXML
    Pane userMessageDetailPane;
    @FXML
    Label editMessageLabel;
    @FXML
    Label replyMessageInMyLabel;
    @FXML
    Label replyMessageInUserLabel;
    @FXML
    TextField messageTextField;
    @FXML
    ImageView editMessageIcon;
    @FXML
    TableView<HelloController.Test> groupChatTable;
    @FXML
    MenuItem blockLabel;
    @FXML
    SplitMenuButton moreOption;
    @FXML
    Label replyMessageLabel;
    @FXML
    Pane replyMessagePane;
    @FXML
    TextField searchUserToChatTextField;
    @FXML
    Label notFoundInSearchToChatLabel;
    @FXML
    Pane groupDetailPane;
    @FXML
    Label groupNameInDetail;
    @FXML
    Label groupMembersInDetail;
    @FXML
    ImageView groupProfileInDetail;
    @FXML
    TableView<HelloController.Test> groupMemberTable;
    @FXML
    Rectangle blackBackGround;
    @FXML
    SplitMenuButton moreOptionInGroupDetail;
    @FXML
    TextField addMemberTextField;
    @FXML
    Button addMemberButton;
    @FXML
    Label notFoundInAddMemberLabel;
    @FXML
    Pane removeBanPane;
    @FXML
    Label banUnbanLabel;
    @FXML
    CheckBox groupCheckBox;
    @FXML
    CheckBox directMessageCheckBox;
    @FXML
    TextField createNewGroupTextField;
    @FXML
    Button createNewGroupButton;
    @FXML
    SplitMenuButton createNewGroupSplitMenu;

    int selectedDirectMessageID;
    int selectedGroupID;
    int selectedMessageID;
    boolean inDirectMessage;
    int blockCondition;
    boolean isBan;
    int replyToMessageID = 0;
    int forwardMessageID = 0;
    boolean forwardFromDirectMessage;
    boolean isAdmin;
    int selectedMemberID;
    boolean sendPhoto;

    public void showChatPage() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);
        stage.getIcons().add(icon);
        stage.setTitle("chat page");
        stage.setScene(scene);
        stage.show();
        HelloController controller = loader.getController();
        controller.setChatPage();
    }

    public void setChatPage () throws SQLException {
        themeImage = new Image(User.theme);
        themeInChat.setImage(themeImage);
        TableColumn<HelloController.Test,ImageView> one = new TableColumn<>("profile");
        one.setCellValueFactory(new PropertyValueFactory<>("profile"));
        one.setPrefWidth(50);
        TableColumn<HelloController.Test,Object> two = new TableColumn<>("label");
        two.setCellValueFactory(new PropertyValueFactory<>("object"));
        two.setPrefWidth(155);
        chatListTable.setOnMouseClicked(chatListTableEventHandler);
        chatListTable.getColumns().clear();
        chatListTable.getColumns().add(one);
        chatListTable.getColumns().add(two);
        chatListTable.getItems().clear();
        chatListTable.setItems(FXCollections.observableList(getChatList()));
    }

    public ArrayList<Test> getChatList() throws SQLException {
        ArrayList<Test> arrayList = new ArrayList<>();
        ArrayList<String> directMessageUserName =  SQL.getFollowersUserNameByID(User.myID);
        if (directMessageCheckBox.isSelected()) {
            for (String s : directMessageUserName) {
                int unSeen = SQL.getUnSeenCount(User.myID,SQL.getIDByUserName(s));
                Label label = new Label(s);
                if (unSeen != 0)
                    label.setText(label.getText() + " (" + unSeen + ")");
                label.setPrefHeight(50);
                label.setPrefWidth(150);
                label.setAlignment(Pos.CENTER_LEFT);
                label.setStyle("-fx-label-padding: 10 px;-fx-background-color: #4947c9;");
                label.setTextFill(Color.WHITE);
                label.setFont(Font.font(20));
                ImageView imageView = new ImageView(SQL.getProfileImageByID(SQL.getIDByUserName(s)));
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                imageView.setPreserveRatio(false);
                arrayList.add(new Test(imageView,label));
            }
            for (Integer userID : SQL.getDirectMessageBefore(User.myID)) {
                String userName = SQL.getUserNameByID(userID);
                if (!directMessageUserName.contains(userName)) {
                    int unSeen = SQL.getUnSeenCount(User.myID,SQL.getIDByUserName(userName));
                    Label label = new Label(userName);
                    if (unSeen != 0)
                        label.setText(label.getText() + " (" + unSeen + ")");
                    label.setPrefHeight(50);
                    label.setPrefWidth(150);
                    label.setAlignment(Pos.CENTER_LEFT);
                    label.setStyle("-fx-label-padding: 10 px;-fx-background-color: red;");
                    label.setTextFill(Color.WHITE);
                    label.setFont(Font.font(20));
                    ImageView imageView = new ImageView(SQL.getProfileImageByID(userID));
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    imageView.setPreserveRatio(false);
                    arrayList.add(new Test(imageView,label));
                }
            }
        }
        if (groupCheckBox.isSelected()) {
            for (Integer integer : SQL.getAllMyGroupName(User.myID)) {
                int unSeen = SQL.getUnSeenCountInGroup(integer,User.myID);
                Label label = new Label(SQL.getGroupNameByID(integer));
                if (unSeen != 0)
                    label.setText(label.getText() + " (" + unSeen + ")");
                label.setPrefHeight(50);
                label.setPrefWidth(150);
                label.setAlignment(Pos.CENTER_LEFT);
                label.setStyle("-fx-label-padding: 10 px;-fx-background-color: green;");
                label.setTextFill(Color.WHITE);
                label.setFont(Font.font(20));
                label.setId(Integer.toString(integer));
                ImageView imageView = new ImageView(SQL.getGroupProfileByID(integer));
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                imageView.setPreserveRatio(false);
                arrayList.add(new Test(imageView,label));
            }
        }
        return arrayList;
    }

    EventHandler<MouseEvent> chatListTableEventHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            chatPageListView.setOnMouseClicked(chatPageListViewEventHandler);
            try {
                inDirectMessage = ((Label)chatListTable.getSelectionModel().getSelectedItem().getObject()).getId() == null;
                if (inDirectMessage) {
                    String x = ((Label)chatListTable.getSelectionModel().getSelectedItem().getObject()).getText();
                    if (x.contains("(")) {
                        selectedDirectMessageID = SQL.getIDByUserName(x.substring(0, x.indexOf("(") - 1));
                        SQL.seenMessageInDirectMessage(User.myID, selectedDirectMessageID);
                        ((Label)chatListTable.getSelectionModel().getSelectedItem().getObject()).setText(x.substring(0, x.indexOf("(") - 1));
                    }
                    else
                        selectedDirectMessageID = SQL.getIDByUserName(x);
                    blockCondition = SQL.checkBlock(User.myID,selectedDirectMessageID);
                    if (forwardMessageID != 0 && blockCondition == -1) {
                        int origin;
                        String content;
                        if (forwardFromDirectMessage) {
                            origin = SQL.getForwardedIDByDirectMessageID(forwardMessageID);
                            if (origin == 0) origin = SQL.getSenderIDFromDirectMessageID(forwardMessageID);
                            content = SQL.getContentDirectMessageByID(forwardMessageID);
                        }
                        else {
                            origin = SQL.getForwardedIDFromGroupMessage(forwardMessageID);
                            if (origin == 0) origin = SQL.getSenderIDFromGroupMessage(forwardMessageID);
                            content = SQL.getGroupContentByID(forwardMessageID);
                        }
                        SQL.forwardMessageInDirectMessage(User.myID, selectedDirectMessageID,content,origin,java.time.LocalTime.now().toString().substring(0, 8));
                    }
                    showDirectChat(selectedDirectMessageID,blockCondition);
                }
                else {
                    Label label = ((Label)chatListTable.getSelectionModel().getSelectedItem().getObject());
                    selectedGroupID = Integer.parseInt((label.getId()));
                    if (label.getText().contains("(")) {
                        SQL.seenMessageInGroup(selectedGroupID,User.myID);
                        label.setText(label.getText().substring(0,label.getText().indexOf("(")-1));
                    }
                    isBan = SQL.isBanInGroup(User.myID,selectedGroupID);
                    isAdmin = SQL.isAdmin(User.myID,selectedGroupID);
                    if (forwardMessageID != 0 &&  !isBan) {
                        int origin;
                        String content;
                        if (forwardFromDirectMessage) {
                            origin = SQL.getForwardedIDByDirectMessageID(forwardMessageID);
                            if (origin == 0) origin = SQL.getSenderIDFromDirectMessageID(forwardMessageID);
                            content = SQL.getContentDirectMessageByID(forwardMessageID);
                        }
                        else {
                            origin = SQL.getForwardedIDFromGroupMessage(forwardMessageID);
                            if (origin == 0) origin = SQL.getSenderIDFromGroupMessage(forwardMessageID);
                            content = SQL.getGroupContentByID(forwardMessageID);
                        }
                        SQL.forwardMessageInGroup(selectedGroupID, User.myID,content,origin,java.time.LocalTime.now().toString().substring(0, 8));
                    }
                    showGroupMessage(selectedGroupID);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            forwardMessageID = 0;
        }
    };

    EventHandler<MouseEvent> chatPageListViewEventHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                try {
                    selectedMessageID = Integer.parseInt(((Label) chatPageListView.getSelectionModel().getSelectedItem()).getId());
                }
                catch (ClassCastException ignored) {
                    selectedMessageID = Integer.parseInt(((ImageView) chatPageListView.getSelectionModel().getSelectedItem()).getId());
                }
                try {
                    if (SQL.canDeleteMessageInDirect(User.myID,selectedMessageID)) {
                        myMessageDetailPane.setLayoutX(mouseEvent.getSceneX()+5);
                        myMessageDetailPane.setLayoutY(mouseEvent.getSceneY() - 5);
                        editMessageLabel.setDisable(blockCondition!=-1 || !SQL.canEditMessageInDirect(User.myID,selectedMessageID));
                        replyMessageInMyLabel.setDisable(blockCondition!=-1);
                        userMessageDetailPane.setVisible(false);
                        myMessageDetailPane.setVisible(true);
                    }
                    else {
                        userMessageDetailPane.setLayoutX(mouseEvent.getSceneX()+5);
                        userMessageDetailPane.setLayoutY(mouseEvent.getSceneY());
                        replyMessageInUserLabel.setDisable(blockCondition!=-1);
                        myMessageDetailPane.setVisible(false);
                        userMessageDetailPane.setVisible(true);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                myMessageDetailPane.setVisible(false);
                userMessageDetailPane.setVisible(false);
            }
        }
    };

    EventHandler<MouseEvent> groupChatTableEventHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                try {
                    selectedMessageID = Integer.parseInt(((Label) groupChatTable.getSelectionModel().getSelectedItem().getObject()).getId());
                }
                catch (ClassCastException ignored) {
                    selectedMessageID = Integer.parseInt(((ImageView) groupChatTable.getSelectionModel().getSelectedItem().getObject()).getId());
                }
                try {
                    if (SQL.canDeleteMessageInGroup(User.myID,selectedGroupID,selectedMessageID)) {
                        myMessageDetailPane.setLayoutX(mouseEvent.getSceneX()+5);
                        myMessageDetailPane.setLayoutY(mouseEvent.getSceneY()-5);
                        editMessageLabel.setDisable(isBan || !SQL.canEditMessageInGroup(User.myID,selectedGroupID,selectedMessageID));
                        replyMessageInMyLabel.setDisable(isBan);
                        userMessageDetailPane.setVisible(false);
                        myMessageDetailPane.setVisible(true);

                    }
                    else {
                        userMessageDetailPane.setLayoutX(mouseEvent.getSceneX()+5);
                        userMessageDetailPane.setLayoutY(mouseEvent.getSceneY());
                        replyMessageInUserLabel.setDisable(isBan);
                        myMessageDetailPane.setVisible(false);
                        userMessageDetailPane.setVisible(true);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                myMessageDetailPane.setVisible(false);
                userMessageDetailPane.setVisible(false);
            }
        }
    };

    public void showDirectChat (int idToChat,int blockInt) throws SQLException {
        messageTextField.setDisable(blockInt!=-1);
        if (blockInt == 1) blockLabel.setText("unblock");
        else blockLabel.setText("block");
        moreOption.setVisible(true);
        chatNameLabel.setText(SQL.getUserNameByID(idToChat));
        chatProfileImageView.setImage(new Image(SQL.getProfileImageByID(idToChat)));
        chatFormatDirectMessage(idToChat);
        groupChatTable.setVisible(false);
        chatPageListView.setVisible(true);
    }

    public void chatFormatDirectMessage (int IDToChat) throws SQLException {
        chatPageListView.getItems().clear();
        for (String s : SQL.getChatContent(User.myID,IDToChat)) {
            ImageView imageView;
            boolean myChat = s.contains("YOU  :");
            s = s.substring(s.indexOf(" : ") + 3);
            String id = s.substring(s.indexOf("$")+1);
            s = s.substring(0,s.indexOf("$"));
            if (s.contains("photo***") && !s.contains("Replied To")) {
                if (s.contains("Forwarded From")) {
                    Label label = new Label(s.substring(s.indexOf("Forwarded From"),s.indexOf("-")));
                    s = s.replace(label.getText(),"");
                    label.setText("Photo " + label.getText());
                    label.setWrapText(true);
                    label.setTextFill(Color.WHITE);
                    label.setPrefWidth(505);
                    label.setFont(Font.font(18));
                    label.setId(id);
                    if (myChat) {
                        label.setStyle("-fx-background-color: blue; -fx-label-padding: 10px 5px 10px 5px;");
                        label.setAlignment(Pos.CENTER_RIGHT);
                    }
                    else label.setStyle("-fx-background-color:  #4f090c; -fx-label-padding: 10px 5px 10px 5px;");
                    chatPageListView.getItems().add(label);
                }
                imageView = new ImageView(s.substring(s.lastIndexOf("*") + 1,s.indexOf("-")-1));
                imageView.setFitWidth(240);
                imageView.setFitHeight(170);
                imageView.setPreserveRatio(false);
                imageView.setId(id);
                if (myChat)
                    imageView.setTranslateX(265);
                chatPageListView.getItems().add(imageView);
                continue;
            }
            Label label = new Label(s);
            label.setWrapText(true);
            label.setTextFill(Color.WHITE);
            label.setPrefWidth(505);
            label.setFont(Font.font(18));
            label.setId(id);
            if (myChat) {
                label.setStyle("-fx-background-color: blue; -fx-label-padding: 10px 5px 10px 5px;");
                label.setAlignment(Pos.CENTER_RIGHT);
            }
            else label.setStyle("-fx-background-color:  #4f090c; -fx-label-padding: 10px 5px 10px 5px;");
            chatPageListView.getItems().add(label);
        }
    }

    public void showGroupMessage (int groupID) throws SQLException {
        moreOption.setVisible(false);
        messageTextField.setDisable(isBan);
        chatNameLabel.setText(SQL.getGroupNameByID(groupID));
        chatProfileImageView.setImage(new Image(SQL.getGroupProfileByID(groupID)));
        TableColumn<HelloController.Test,ImageView> one = new TableColumn<>("imageView");
        one.setCellValueFactory(new PropertyValueFactory<>("profile"));
        one.setPrefWidth(40);
        TableColumn<HelloController.Test,Object> two = new TableColumn<>("label");
        two.setCellValueFactory(new PropertyValueFactory<>("object"));
        two.setPrefWidth(480);
        groupChatTable.setOnMouseClicked(groupChatTableEventHandler);
        groupChatTable.getColumns().clear();
        groupChatTable.getColumns().add(one);
        groupChatTable.getColumns().add(two);
        groupChatTable.getItems().clear();
        chatFormatGroup(groupID);
        groupChatTable.setVisible(true);
        chatPageListView.setVisible(false);
    }

    public void chatFormatGroup (int groupID) throws SQLException {
        for (String message : SQL.getGroupMessage(User.myID, groupID)) {
            ImageView photo;
            String profile;
            boolean myMessage = message.contains("YOU : ");
            if (myMessage) { profile = "white.png"; message = message.replace("YOU : ",""); }
            else profile = SQL.getProfileImageByID(SQL.getIDByUserName(message.substring(0,message.indexOf(":")-1)));
            String messageID = message.substring(message.indexOf("$")+1);
            message = message.substring(0,message.indexOf("$"));
            ImageView imageView = new ImageView(profile);
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            imageView.setPreserveRatio(false);
            if (message.contains("photo***") && !message.contains("Replied To")) {
                if (message.contains("Forwarded From")) {
                    Label label = new Label(message.substring(message.indexOf("Forwarded From"),message.indexOf("-")));
                    message = message.replace(label.getText(),"");
                    label.setText("Photo " + label.getText());
                    if (!myMessage)
                    label.setText(message.substring(0,message.indexOf(":"))+ " : " + label.getText());
                    label.setWrapText(true);
                    label.setTextFill(Color.WHITE);
                    label.setPrefWidth(505);
                    label.setFont(Font.font(18));
                    label.setId(messageID);
                    if (myMessage) {
                        label.setStyle("-fx-background-color: blue; -fx-label-padding: 10px 5px 10px 5px;");
                        label.setAlignment(Pos.CENTER_RIGHT);
                    }
                    else label.setStyle("-fx-background-color:  #4f090c; -fx-label-padding: 10px 5px 10px 5px;");
                    groupChatTable.getItems().add(new Test(imageView,label));
                }
                photo = new ImageView(message.substring(message.lastIndexOf("*") + 1,message.indexOf("-")-1));
                photo.setFitWidth(240);
                photo.setFitHeight(170);
                photo.setPreserveRatio(false);
                photo.setId(messageID);
                if (myMessage)
                    photo.setTranslateX(265);
                groupChatTable.getItems().add(new Test(imageView,photo));
                continue;
            }
            Label label = new Label();
            label.setText(message);
            label.setWrapText(true);
            label.setTextFill(Color.WHITE);
            label.setPrefWidth(475);
            label.setFont(Font.font(18));
            label.setId(messageID);
            if (myMessage) { label.setStyle("-fx-background-color: blue; -fx-label-padding: 10px 5px 10px 5px;"); label.setAlignment(Pos.CENTER_RIGHT);}
            else label.setStyle("-fx-background-color:  #4f090c; -fx-label-padding: 10px 5px 10px 5px;");
            groupChatTable.getItems().add(new Test(imageView,label));
        }
    }

    public void hover (MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        label.setStyle("-fx-background-color: #B2BEB5;");
    }

    public void undoHover (MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        label.setStyle("-fx-background-color: white;");
    }

    public void sendMessage () throws SQLException {
        String content;
        if (sendPhoto) try { content = "photo***" + chooseFile(messageTextField.getScene().getWindow()); } catch (NullPointerException ignored) {sendPhoto = false; return;}
        else content = messageTextField.getText();
        if (inDirectMessage) {
            if (blockCondition!=-1) return;
            if (replyToMessageID == 0)
                SQL.insertNewDirectMessage(User.myID, selectedDirectMessageID,content,java.time.LocalTime.now().toString().substring(0, 8));
            else
                SQL.replyMessageInDirectMessage(User.myID,selectedDirectMessageID,content,replyToMessageID,java.time.LocalTime.now().toString().substring(0, 8));
            showDirectChat(selectedDirectMessageID,blockCondition);
        }
        else {
            if (isBan) return;
            if (replyToMessageID == 0)
                SQL.insertNewMessageIntoGroup(selectedGroupID,User.myID,content,java.time.LocalTime.now().toString().substring(0, 8));
            else
                SQL.replyMessageInGroup(selectedGroupID,User.myID,content,replyToMessageID,java.time.LocalTime.now().toString().substring(0, 8));
            showGroupMessage(selectedGroupID);
        }
        replyToMessageID = 0;
        messageTextField.clear();
        replyMessagePane.setVisible(false);
        sendPhoto = false;
    }

    public void setChooseImage () throws SQLException {
        sendPhoto = true;
        sendMessage();
    }

    public void setEdit () throws SQLException {
        userMessageDetailPane.setVisible(false);
        myMessageDetailPane.setVisible(false);
        if (inDirectMessage)
            messageTextField.setText(SQL.getContentDirectMessageByID(selectedMessageID));
        else
            messageTextField.setText(SQL.getGroupContentByID(selectedMessageID));
        editMessageIcon.setVisible(true);
    }

    public void EditMessage () throws SQLException {
        if (inDirectMessage) {
            SQL.editMessageInDirectMessage(selectedMessageID, messageTextField.getText());
            showDirectChat(selectedDirectMessageID,blockCondition);
        }
        else {
            SQL.editMessageInGroup(selectedMessageID,messageTextField.getText());
            showGroupMessage(selectedGroupID);
        }
        messageTextField.clear();
        editMessageIcon.setVisible(false);
    }

    public void deleteMessage () throws SQLException {
        if (inDirectMessage) {
            SQL.deleteMessageInDirectMessage(selectedMessageID);
            showDirectChat(selectedDirectMessageID,blockCondition);
        }
        else {
            SQL.deleteMessageInGroup(selectedMessageID);
            showGroupMessage(selectedGroupID);
        }
        userMessageDetailPane.setVisible(false);
        myMessageDetailPane.setVisible(false);
    }

    public void blockAndUnblock () throws SQLException {
        if (blockLabel.getText().equals("block"))
            SQL.blockUser(User.myID,selectedDirectMessageID);
        else
            SQL.unBlockUser(User.myID,selectedDirectMessageID);
        blockCondition = SQL.checkBlock(User.myID,selectedDirectMessageID);
        showDirectChat(selectedDirectMessageID,blockCondition);
    }

    public void setReply () throws SQLException {
        myMessageDetailPane.setVisible(false);
        userMessageDetailPane.setVisible(false);
        replyToMessageID = selectedMessageID;
        if (inDirectMessage)
            replyMessageLabel.setText(SQL.getContentDirectMessageByID(selectedMessageID));
        else
            replyMessageLabel.setText(SQL.getGroupContentByID(selectedMessageID));
        replyMessagePane.setVisible(true);
    }

    public void cancelReply () {
        replyToMessageID = 0;
        replyMessagePane.setVisible(false);
    }

    public void setForward () {
        myMessageDetailPane.setVisible(false);
        userMessageDetailPane.setVisible(false);
        forwardMessageID = selectedMessageID;
        forwardFromDirectMessage = inDirectMessage;
    }

    public void searchUserToChat () throws SQLException {
        int userID = SQL.getIDByUserName(searchUserToChatTextField.getText());
        searchUserToChatTextField.clear();
        notFoundInSearchToChatLabel.setVisible(userID==-1);
        if (userID != -1) {
            blockCondition = SQL.checkBlock(User.myID,userID);
            inDirectMessage = true;
            selectedDirectMessageID = userID;
            showDirectChat(selectedDirectMessageID,blockCondition);
        }
    }

    public void showGroupDetail () throws SQLException {
        if (inDirectMessage) return;
        groupDetailPane.setVisible(true);
        blackBackGround.setVisible(true);
        groupProfileInDetail.setImage(new Image(SQL.getGroupProfileByID(selectedGroupID)));
        groupNameInDetail.setText(SQL.getGroupNameByID(selectedGroupID));
        ArrayList<Integer> groupMembers = SQL.getGroupMembersID(selectedGroupID);
        groupMembersInDetail.setText(groupMembers.size() + " members");
        TableColumn<HelloController.Test,ImageView> one = new TableColumn<>("profile");
        one.setCellValueFactory(new PropertyValueFactory<>("profile"));
        one.setPrefWidth(50);
        TableColumn<HelloController.Test,Object> two = new TableColumn<>("object");
        two.setCellValueFactory(new PropertyValueFactory<>("object"));
        two.setPrefWidth(185);
        groupMemberTable.getColumns().add(one);
        groupMemberTable.getColumns().add(two);
        groupMemberTable.setOnMouseClicked(memberTableEventHandler);
        ArrayList<Test> testArrayList = new ArrayList<>();
        for (Integer memberID : groupMembers) {
            String admin = "";
            if (SQL.isAdmin(memberID,selectedGroupID)) admin = " (Admin)";
            Label label = new Label(SQL.getUserNameByID(memberID) + admin);
            label.setPrefHeight(50);
            label.setPrefWidth(180);
            label.setAlignment(Pos.CENTER_LEFT);
            if (SQL.isBanInGroup(memberID,selectedGroupID))
                label.setStyle("-fx-label-padding: 10 px;-fx-background-color: red;");
            else
                label.setStyle("-fx-label-padding: 10 px;-fx-background-color: #4947c9;");
            label.setTextFill(Color.WHITE);
            label.setFont(Font.font(20));
            label.setId(Integer.toString(memberID));
            ImageView imageView = new ImageView(SQL.getProfileImageByID(memberID));
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            imageView.setPreserveRatio(false);
            testArrayList.add(new Test(imageView,label));
        }
        groupMemberTable.setItems(FXCollections.observableList(testArrayList));
    }

    public void setProfileForGroup () throws SQLException {
        String path = chooseFile(groupMembersInDetail.getScene().getWindow());
        SQL.setProfileForGroup(selectedGroupID,path);
        groupProfileInDetail.setImage(new Image(path));
        chatListTable.getItems().clear();
        chatListTable.setItems(FXCollections.observableList(getChatList()));
        showGroupMessage(selectedGroupID);
    }

    public String chooseFile (Window window) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\Max\\Desktop\\graphicalProject\\src\\main\\resources"));
        File selectedFile = fileChooser.showOpenDialog(window);
        return selectedFile.getName();
    }

    public void setAddMember () {
        addMemberButton.setVisible(true);
        addMemberTextField.setVisible(true);
    }

    public void addMemberToGroup () throws SQLException {
        int userID = SQL.getIDByUserName(addMemberTextField.getText());
        addMemberTextField.clear();
        if (userID==-1) {notFoundInAddMemberLabel.setText("User Not Found"); notFoundInAddMemberLabel.setVisible(true); }
        else if (SQL.isMemberOfGroup(userID,selectedGroupID)) {notFoundInAddMemberLabel.setText("User Is Already Member Of Group"); notFoundInAddMemberLabel.setVisible(true); }
        else if (!SQL.followsMe(User.myID,userID)) {notFoundInAddMemberLabel.setText("User doesn't follow you"); notFoundInAddMemberLabel.setVisible(true);}
        else {
            SQL.addMemberToGroup(selectedGroupID,userID);
            closeGroupDetail();
            showGroupDetail();
        }
        addMemberButton.setVisible(false);
        addMemberTextField.setVisible(false);
    }

    public void unVisible (MouseEvent mouseEvent) {
        ((Label) mouseEvent.getSource()).setVisible(false);
    }

    public void closeGroupDetail () {
        groupDetailPane.setVisible(false);
        blackBackGround.setVisible(false);
        addMemberButton.setVisible(false);
        addMemberTextField.setVisible(false);
        notFoundInAddMemberLabel.setVisible(false);
        groupMemberTable.getItems().clear();
        groupMemberTable.getColumns().clear();
    }

    EventHandler<MouseEvent> memberTableEventHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            selectedMemberID = Integer.parseInt(((Label) groupMemberTable.getSelectionModel().getSelectedItem().getObject()).getId());
            try {
                if (SQL.isAdmin(selectedMemberID,selectedGroupID)) return;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && isAdmin) {
                removeBanPane.setLayoutX(mouseEvent.getSceneX() + 5);
                removeBanPane.setLayoutY(mouseEvent.getSceneY() + 5);
                try {
                    if (SQL.isBanInGroup(selectedMemberID,selectedGroupID))
                        banUnbanLabel.setText("unban");
                    else
                        banUnbanLabel.setText("ban");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                removeBanPane.setVisible(true);
            }
            else if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
                removeBanPane.setVisible(false);
        }
    };

    public void removeMember () throws SQLException {
        SQL.removeMemberFromGroup(selectedGroupID,selectedMemberID);
        removeBanPane.setVisible(false);
        closeGroupDetail();
        showGroupDetail();
    }

    public void banUnBanMember () throws SQLException {
        if (banUnbanLabel.getText().equals("ban"))
            SQL.banMember(selectedGroupID,selectedMemberID);
        else
            SQL.unbanMemberFromGroup(selectedGroupID,selectedMemberID);
        removeBanPane.setVisible(false);
        closeGroupDetail();
        showGroupDetail();
    }

    public void chatFilter (MouseEvent mouseEvent) throws SQLException {
       boolean selectedIsGroup = Objects.equals(mouseEvent.getSource(),groupCheckBox);
       if (selectedIsGroup) {
           if (!directMessageCheckBox.isSelected())
               directMessageCheckBox.setSelected(true);
       }
       else {
           if (!groupCheckBox.isSelected())
               groupCheckBox.setSelected(true);
       }
        chatListTable.getItems().clear();
        chatListTable.setItems(FXCollections.observableList(getChatList()));
    }

    public void setCreateNewGroup () {
        createNewGroupSplitMenu.setVisible(false);
        createNewGroupButton.setVisible(true);
        createNewGroupTextField.setVisible(true);
    }

    public void createNewGroup () throws SQLException {
        createNewGroupSplitMenu.setVisible(true);
        createNewGroupButton.setVisible(false);
        createNewGroupTextField.setVisible(false);
        SQL.createNewGroup(User.myID,createNewGroupTextField.getText());
        createNewGroupTextField.clear();
        chatListTable.getItems().clear();
        chatListTable.setItems(FXCollections.observableList(getChatList()));
    }

    public record Test(ImageView profile, Object object) {

        public ImageView getProfile() {
            return profile;
        }

        public Object getObject() {
            return object;
        }
    }
}