package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class SQL  {

    private static final Statement statement = Repository.statement;

    public static void insertNewUser (String username,String password,int SQNumber,String answer,String accountType) throws SQLException {

        statement.execute("INSERT INTO users (UserName, Password, SQNumber, Answer, AccountType,ProfileImage) values ('" + username+ "','" +password + "','" + SQNumber + "','" + answer + "','" + accountType + "','default profile.jpg')");
    }
    public static String getProfileImageByID(int ID) throws SQLException
    {
        String profileImage = " ";
        String sql = "SELECT ProfileImage FROM users WHERE ID = '"+ID+"';";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next())
        {
            profileImage=resultSet.getString("ProfileImage");
        }
        return profileImage;
    }
    public static int getIDByUserName (String username) throws SQLException
    {
        String sql = "SELECT ID from users where UserName = '" + username + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        if (!resultSet.next())
        {
            return -1;
        }
        return resultSet.getInt("ID");
    }
    public static String getUserNameByID (int ID) throws SQLException
    {
        String sql = "SELECT UserName FROM users WHERE ID = '"+ID+"';";
        ResultSet resultSet=statement.executeQuery(sql);
        if(!resultSet.next())
        {
            return " ";
        }
        return resultSet.getString("UserName");
    }
    public static String getPasswordByID (int ID) throws SQLException
    {
        String sql = "SELECT Password from users where ID = '" + ID + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getString("Password");
    }
    public static int getSQNumberByID (int ID) throws SQLException
    {
        String sql = "SELECT SQNumber FROM users WHERE ID = '" + ID + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getInt("SQNumber");
    }
    public static String getAnswerByID (int ID) throws SQLException
    {
        String sql = "SELECT answer FROM users WHERE ID = '" + ID + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getString("answer");
    }
    public static boolean getBoolADByID(int ID) throws SQLException
    {
        String sql = "SELECT AccountType FROM users WHERE ID = '"+ ID + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return !resultSet.getString("AccountType").equals("personal");
    }
    public static void setNewPasswordByID (int ID,String newPassword) throws SQLException
    {
        String sql = "UPDATE users SET Password = '" +newPassword + "' WHERE ID = '" + ID + "';" ;
        statement.execute(sql);
    }
    public static ArrayList<String> getFollowingsUserNameByID (int ID) throws SQLException
    {
        ArrayList<Integer> followingsID= new ArrayList<>();
        ArrayList<String> followingsUserName= new ArrayList<>();
        String sql = "SELECT FollowingID FROM relations WHERE FollowerID = '"+ID+"';";
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next())
        {
            followingsID.add(resultSet.getInt("FollowingID"));
        }
        int i;
        for(i=followingsID.size()-1;i>=0;i--)
        {
            followingsUserName.add(SQL.getUserNameByID(followingsID.get(i)));
        }
        return followingsUserName;
    }
    public static ArrayList<Integer> getFollowingsPostsIDByID (int ID) throws SQLException
    {
        ArrayList<Integer> postsID= new ArrayList<>();
        ArrayList<Integer> followingsID=SQL.getFollowingsIDByID(ID);
        if(followingsID.size()!=0)
        {
            StringBuilder sql = new StringBuilder("SELECT ID FROM contents WHERE");
            int i;
            for (i = 0; i < followingsID.size(); i++)
            {
                sql.append(" ( UserID = '").append(followingsID.get(i)).append("' AND BoolPost = 1)");
                if (i != followingsID.size() - 1)
                {
                    sql.append(" OR");
                }
            }
            ResultSet resultSet = statement.executeQuery(sql.toString());
            while (resultSet.next())
            {
                postsID.add(resultSet.getInt("ID"));
            }
        }
        return postsID;
    }
    public static ArrayList<Integer> getFollowingsIDByID(int ID) throws SQLException
    {
        ArrayList<Integer> followingsID= new ArrayList<>();
        String sql = "SELECT FollowingID FROM relations WHERE FollowerID = '"+ID+"';";
        ResultSet resultSet=statement.executeQuery(sql);
        while(resultSet.next())
        {
            followingsID.add(resultSet.getInt("FollowingID"));
        }
        return followingsID;
    }
    public static ArrayList<String> getFollowersUserNameByID(int ID) throws SQLException
    {
        ArrayList<Integer> followersID= new ArrayList<>();
        ArrayList<String> followersUserName= new ArrayList<>();
        String sql = "SELECT FollowerID FROM relations WHERE FollowingID = '"+ID+"';";
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next())
        {
            followersID.add(resultSet.getInt("FollowerID"));
        }
        int i;
        for(i=followersID.size()-1;i>=0;i--)
        {
            followersUserName.add(SQL.getUserNameByID(followersID.get(i)));
        }
        return followersUserName;
    }
    public static void follow(int followerID,int followingID) throws SQLException
    {
        String sql = "INSERT INTO relations values ('" + followerID +"','"+followingID+"');";
        statement.execute(sql);
    }
    public static void unfollow(int followerID,int followingID) throws SQLException
    {
        String sql = "DELETE FROM relations WHERE FollowerID = '"+followerID+"' AND FollowingID = '"+followingID+"';";
        statement.execute(sql);
    }
    public static void createNewPost(int userID,String content,boolean AD,String image) throws SQLException
    {
        int ad;
        if(AD)
        {
            ad=1;
        }
        else
        {
            ad=0;
        }
        String sql = "INSERT INTO contents (UserID,Content,BoolAdPost,BoolPost,ContentID,Image) VALUES ('"+userID+"','"+content+"','"+ad+ "','1','0','"+image+"');";
        statement.execute(sql);
    }
    public static ArrayList<Integer> getPostsIDByUserID(int UserID) throws SQLException
    {
        ArrayList<Integer> postsID= new ArrayList<>();
        String sql = "SELECT ID FROM contents Where UserID = '"+UserID+"' AND BoolPost = '1';";
        ResultSet resultSet=statement.executeQuery(sql);
        while(resultSet.next())
        {
            postsID.add(resultSet.getInt("ID"));
        }
        return postsID;
    }
    public static void removePostByID(int ContentID) throws SQLException
    {
        String sql = "DELETE FROM contents WHERE ID = '"+ContentID+"';";
        statement.execute(sql);
    }
    public static String getContentByContentID(int ContentID) throws SQLException
    {
        String Content = " ";
        String sql = "SELECT Content FROM contents WHERE ID = '"+ContentID+"';";
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next())
        {
            Content = resultSet.getString("Content");
        }
        return Content;
    }
    public static String getImageByContentID(int ContentID) throws SQLException
    {
        String image = " ";
        String sql = "SELECT Image FROM contents WHERE ID = '"+ContentID+"'";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next())
        {
            image = resultSet.getString("Image");
        }
        return image;
    }
    public static void like(int UserID,int ContentID,String Date) throws SQLException
    {
        String sql = "INSERT INTO likes (UserID,ContentID,Date) VALUES ('"+UserID+"','"+ContentID+"','"+Date+"');";
        statement.execute(sql);
    }
    public static void unlike(int UserID,int ContentID) throws SQLException
    {
        String sql = "DELETE FROM likes WHERE UserID = '"+UserID+"' AND ContentID = '"+ContentID+"';";
        statement.execute(sql);
    }
    public static boolean liked(int UserID,int ContentID) throws SQLException
    {
        String sql = "SELECT *FROM likes WHERE ContentID = "+ContentID;
        ResultSet resultSet=statement.executeQuery(sql);
        boolean liked = false;
        while(resultSet.next())
        {
            if(resultSet.getInt("UserID")==UserID)
            {
                liked=true;
                break;
            }
        }
        return liked;
    }
    public static int likesNumber(int ContentID) throws SQLException
    {
        int likes = 0;
        String sql = "SELECT *FROM likes WHERE ContentID = "+ContentID;
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next())
        {
            likes++;
        }
        return likes;
    }
    public static void comment(int UserID,int ContentID,String Content) throws SQLException
    {
        String sql = "INSERT INTO contents ( UserID , Content , BoolAdPost , BoolPost , ContentID ,Image ) VALUES ('"+UserID+"','"+Content+"','0','0','"+ContentID+"','0')";
        statement.execute(sql);
    }
    public static ArrayList<Integer> getCommentsIDOfPost(int ContentID) throws SQLException
    {
        ArrayList<Integer> contentsID = new ArrayList<>();
        String sql = "SELECT ID FROM contents WHERE ContentID = '"+ContentID+"';";
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next())
        {
            contentsID.add(resultSet.getInt("ID"));
        }
        return contentsID;
    }
    public static void reply(int UserID,int ContentID,String ReplyContent) throws SQLException
    {
        String sql = "INSERT INTO contents (UserID,Content,BoolAdPost,BoolPost,ContentID,Image) VALUES ('"+UserID+"','"+ReplyContent+"','0','0','"+ContentID+"','0');";
        statement.execute(sql);
    }
    public static ArrayList<Integer> getRepliesIDOfComment(int ContentID) throws SQLException
    {
        ArrayList<Integer> repliesID = new ArrayList<>();
        String sql = "SELECT ID FROM contents WHERE ContentID = '"+ContentID+"';";
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next())
        {
            repliesID.add(resultSet.getInt("ID"));
        }
        return repliesID;
    }
    public static ArrayList<Integer> getSuggestedUserIDsByUserID(int UserID) throws SQLException
    {
        ArrayList<Integer> followingIDs = SQL.getFollowingsIDByID(UserID);
        ArrayList<Integer> suggestedUsersIDs = new ArrayList<>();
        int i,j;
        ArrayList<Integer> followings ;
        for(i=0;i<followingIDs.size();i++)
        {
            followings = SQL.getFollowingsIDByID(followingIDs.get(i));
            suggestedUsersIDs.addAll(followings);
        }
        for(i=0;i<followingIDs.size();i++)
        {
            for(j=0;j<suggestedUsersIDs.size();j++)
            {
                if(followingIDs.get(i).equals(suggestedUsersIDs.get(j)))
                {
                    suggestedUsersIDs.remove(j);
                    j--;
                }
            }
        }
        for(i=0;i<suggestedUsersIDs.size();i++)
        {
            for(j=i+1;j<suggestedUsersIDs.size();j++)
            {
                if(suggestedUsersIDs.get(i).equals(suggestedUsersIDs.get(j)))
                {
                    suggestedUsersIDs.remove(j);
                    j--;
                }
            }
        }
        for(i=0;i<suggestedUsersIDs.size();i++)
        {
            if(suggestedUsersIDs.get(i).equals(UserID))
            {
                suggestedUsersIDs.remove(i);
                break;
            }
        }
        return suggestedUsersIDs;
    }
    public static void profileView(int UserID,String Date) throws SQLException
    {
        String sql = "INSERT INTO profileview (UserID,Date) VALUES ('"+UserID+"','"+Date+"');";
        statement.execute(sql);
    }
    public static ArrayList<String> getProfileViewsByID(int UserID) throws SQLException
    {
        String sql = "SELECT Date FROM profileview WHERE UserID = '"+UserID+"';";
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Integer> views = new ArrayList<>();
        ArrayList<String> profileViews = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next())
        {
            dates.add(resultSet.getString("Date"));
        }
        int i,j;
        for(i=0;i< dates.size();i++)
        {
            views.add(1);
            for(j=i+1;j<dates.size();j++)
            {
                if(dates.get(i).equals(dates.get(j)))
                {
                    views.set(i, views.get(i)+1);
                    dates.remove(i);
                    j--;
                }
            }
        }
        for (i=0;i<dates.size();i++)
        {
            profileViews.add(dates.get(i)+" : "+views.get(i)+" views");
        }
        return profileViews;
    }
    public static ArrayList<String> getLikesStats(int ContentID) throws SQLException
    {
        String sql = "SELECT Date From likes WHERE ContentID = '"+ContentID+"';";
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Integer> likes = new ArrayList<>();
        ArrayList<String> likesStats = new ArrayList<>();
        while (resultSet.next())
        {
            dates.add(resultSet.getString("Date"));
        }
        int i,j;
        for(i=0;i<dates.size();i++)
        {
            likes.add(1);
            for(j=i+1;j<dates.size();j++)
            {
                if(dates.get(i).equals(dates.get(j)))
                {
                    likes.set(likes.size()-1,likes.get(likes.size()-1)+1);
                    dates.remove(j);
                    j--;
                }
            }
        }
        for(i=0;i<dates.size();i++)
        {
            likesStats.add(dates.get(i)+" : "+likes.get(i)+" likes");
        }
        return likesStats;
    }
    public static void postView(int ContentID) throws SQLException
    {
        String Date = java.time.LocalDate.now().toString();
        String sql = "INSERT INTO postview (ContentID,Date) VALUES ('"+ContentID+"','"+Date+"')";
        statement.execute(sql);
    }
    public static ArrayList<String> getPostViews(int ContentID) throws SQLException
    {
        String sql = "SELECT Date FROM postview WHERE ContentID = '"+ContentID+"';";
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Integer> views = new ArrayList<>();
        ArrayList<String> postViews = new ArrayList<>();
        while (resultSet.next())
        {
            dates.add(resultSet.getString("Date"));
        }
        int i,j;
        for(i=0;i<dates.size();i++)
        {
            views.add(1);
            for(j=i+1;j<dates.size();j++)
            {
                if(dates.get(i).equals(dates.get(j)))
                {
                    views.set(views.size()-1,views.get(views.size()-1)+1);
                    dates.remove(j);
                    j--;
                }
            }
        }
        for(i=0;i<dates.size();i++)
        {
            postViews.add(dates.get(i)+" : "+views.get(i));
        }
        return postViews;
    }
    public static ArrayList<Integer> getSuggestedPostIDs(int UserID) throws SQLException
    {
        ArrayList<Integer> followingsIDs = SQL.getFollowingsIDByID(UserID);
        ArrayList<Integer> postIDs = new ArrayList<>();
        if(followingsIDs.size()!=0)
        {
            StringBuilder sql = new StringBuilder("SELECT ContentID FROM likes WHERE Date !='0' AND (");
            int i, j;
            for (i = 0; i < followingsIDs.size(); i++)
            {
                sql.append("UserID = '").append(followingsIDs.get(i)).append("'");
                if (i != followingsIDs.size() - 1)
                {
                    sql.append(" OR ");
                }
            }
            sql.append(");");
            ResultSet resultSet = statement.executeQuery(sql.toString());
            while (resultSet.next())
            {
                postIDs.add(resultSet.getInt("ContentID"));
            }
            for (i = 0; i < postIDs.size(); i++)
            {
                for (j = 0; j < followingsIDs.size(); j++)
                {
                    if (SQL.getUserIDByContentID(postIDs.get(i)) == followingsIDs.get(j) || SQL.getUserIDByContentID(postIDs.get(i)) == UserID)
                    {
                        postIDs.remove(i);
                        i--;
                        break;
                    }
                }
            }
            for (i = 0; i < postIDs.size(); i++)
            {
                for (j = i + 1; j < postIDs.size(); j++)
                {
                    if (Objects.equals(postIDs.get(i), postIDs.get(j)))
                    {
                        postIDs.remove(j);
                        j--;
                    }
                }
            }
        }
        return postIDs;
    }
    public static int getUserIDByContentID(int ContentID) throws SQLException
    {
        String sql = "SELECT UserID FROM contents WHERE ID = '"+ContentID+"';";
        ResultSet resultSet = statement.executeQuery(sql);
        int UserID=0;
        while (resultSet.next())
        {
            UserID = resultSet.getInt("UserID");
        }
        return UserID;
    }
    public static void updateProfile(int ID,String profile) throws SQLException
    {
        String sql = "UPDATE users SET ProfileImage = '"+profile+"' WHERE ID = '"+ID+"';";
        statement.execute(sql);
    }
    public static boolean followed(int FollowerID,int FollowingID) throws SQLException
    {
        String sql = "SELECT *FROM relations WHERE FollowerID = '"+FollowerID+"' AND FollowingID = '"+FollowingID+"';";
        ResultSet resultSet = statement.executeQuery(sql);
        boolean followed = false;
        while (resultSet.next())
        {
            followed = true;
        }
        return followed;
    }
    public static ArrayList<Integer> getHomePagePostsIDByID(int UserID) throws SQLException
    {
        ArrayList<Integer> postsID1 = SQL.getFollowingsPostsIDByID(UserID);
        ArrayList<Integer> postsID2 = SQL.getSuggestedPostIDs(UserID);
        ArrayList<Integer> postsID = new ArrayList<>();
        postsID.addAll(postsID1);
        postsID.addAll(postsID2);
        if(postsID.size()!=0)
        {
            StringBuilder sql = new StringBuilder("SELECT ID FROM contents WHERE ");
            int i;
            for (i = 0; i < postsID.size(); i++)
            {
                sql.append("ID = '").append(postsID.get(i)).append("'");
                if (i < postsID.size() - 1)
                {
                    sql.append(" OR ");
                }
            }
            sql.append(";");
            ArrayList<Integer> IDs = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(sql.toString());
            while (resultSet.next())
            {
                IDs.add(resultSet.getInt("ID"));
            }
            return IDs;
        }
        else
        {
            return postsID;
        }
    }

    public static String getContentDirectMessageByID (int ID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT Content FROM directmessage WHERE ID = '" + ID + "';");
        if (!resultSet.next()) return "Message Deleted";
        return resultSet.getString("Content");
    }

    public static ArrayList<String> getChatContent (int myID,int personID) throws SQLException {
        ArrayList<String> chat = new ArrayList<>();
        String between = Math.min(myID,personID) + "&" + Math.max(myID,personID);
        String personName = SQL.getUserNameByID(personID);
        String sql = "SELECT ID,SenderID,Content,RepliedTo,ForwardFrom,Time FROM directmessage WHERE betweenIDs = '" + between + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            StringBuilder stringBuilder = new StringBuilder();
            if (resultSet.getInt("SenderID") == myID) stringBuilder.append("YOU  : ");
            else stringBuilder.append(personName).append(" : ");
            stringBuilder.append(resultSet.getString("Content"));
            int RepliedTo = resultSet.getInt("RepliedTo");
            int ForwardFrom = resultSet.getInt("ForwardFrom");
            if (RepliedTo != 0)
                stringBuilder.append(" Replied To \"").append(RepliedTo).append("\"");
            if (ForwardFrom != 0)
                stringBuilder.append(" Forwarded From '").append(ForwardFrom).append("'");
            stringBuilder.append(" - ").append(resultSet.getString("Time")).append("$").append(resultSet.getInt("ID"));
            chat.add(stringBuilder.toString());
        }
        for (String s : chat) {
            if (s.contains("Replied To")) {
                int replyTo = Integer.parseInt(s.substring(s.indexOf("\"")+1,s.lastIndexOf("\"")));
                String repliedContent = SQL.getContentDirectMessageByID(replyTo);
                chat.set(chat.indexOf(s),s.replace(Integer.toString(replyTo),repliedContent.substring(0,Math.min(repliedContent.length(),15)) + "..."));
            }
            if (s.contains("Forwarded From")) {
                int forwarded = Integer.parseInt(s.substring(s.indexOf("'")+1,s.lastIndexOf("'")));
                String userForwardedFrom = SQL.getUserNameByID(forwarded);
                chat.set(chat.indexOf(s),s.replace("'"+forwarded+"'",userForwardedFrom));
            }
        }
        return chat;
    }

    public static boolean canEditMessageInDirect (int userID,int messageID) throws SQLException {
        String sql = "SELECT SenderID,ForwardFrom FROM directmessage WHERE ID = '" + messageID + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        if (!resultSet.next()) return false;
        return (resultSet.getInt("SenderID") == userID && resultSet.getInt("ForwardFrom") == 0);
    }

    public static boolean canDeleteMessageInDirect (int userID,int messageID) throws SQLException {
        String sql = "SELECT SenderID FROM directmessage WHERE ID = '" + messageID + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        if (!resultSet.next()) return false;
        return resultSet.getInt("SenderID") == userID;
    }

    public static void insertNewDirectMessage (int senderID,int receiverID,String content,String time) throws SQLException {
        String between = Math.min(senderID,receiverID) + "&" + Math.max(senderID,receiverID);
        String sql = "INSERT INTO directmessage (betweenIDs, SenderID, ReceiverID, Content,RepliedTo,ForwardFrom,Time) VALUES ('" + between + "','" + senderID + "','" + receiverID + "','" + content + "','0','0','" + time + "');";
        statement.execute(sql);

    }

    public static void editMessageInDirectMessage (int messageID,String editedMessage) throws SQLException {
        statement.execute("UPDATE directmessage SET Content = '" + editedMessage + "' WHERE ID = '" + messageID + "';");
    }

    public static ArrayList<Integer> getAllMyGroupName (int myID) throws SQLException {
        String x = "&" + myID + "&";
        String sql = "SELECT ID FROM `groups` WHERE GroupMembers LIKE '%" + x + "%';";
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<Integer> groupName = new ArrayList<>();
        while (resultSet.next()) {
            groupName.add(resultSet.getInt("ID"));
        }
        return groupName;
    }

    public static String getGroupNameByID (int groupID) throws SQLException {
        String sql = "SELECT GroupName FROM `groups`WHERE ID = '" + groupID + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        if (!resultSet.next()) return "NOT FOUND";
        return resultSet.getString("GroupName");
    }

    public static String getGroupProfileByID (int groupID) throws SQLException {
        String sql = "SELECT Image FROM `groups`WHERE ID = '" + groupID + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        if (!resultSet.next()) return "NOT FOUND";
        return resultSet.getString("Image");
    }

    public static ArrayList<String> getGroupMessage (int myID,int groupID) throws SQLException {
        ArrayList<String> groupMessage = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT ID,SenderID,Content,RepliedTo,ForwardFrom,Time FROM groupmessage WHERE GroupID = '" + groupID + "';");
        while (resultSet.next()) {
            StringBuilder stringBuilder = new StringBuilder();
            if (resultSet.getInt("SenderID") == myID) stringBuilder.append("YOU : ");
            else stringBuilder.append("@").append(resultSet.getInt("SenderID")).append("@ : ");
            stringBuilder.append(resultSet.getString("Content"));
            int RepliedTo = resultSet.getInt("RepliedTo");
            int ForwardFrom = resultSet.getInt("ForwardFrom");
            if (RepliedTo != 0)
                stringBuilder.append(" Replied To \"").append(RepliedTo).append("\"");
            if (ForwardFrom != 0)
                stringBuilder.append(" Forwarded From '").append(ForwardFrom).append("'");
            stringBuilder.append(" - ").append(resultSet.getString("Time")).append("$").append(resultSet.getInt("ID"));
            groupMessage.add(stringBuilder.toString());
        }
        for (String s : groupMessage) {
            int index = groupMessage.indexOf(s);
            if (s.contains("Replied To")) {
                int replyTo = Integer.parseInt(s.substring(s.indexOf("\"")+1,s.lastIndexOf("\"")));
                String repliedContent = SQL.getGroupContentByID(replyTo);
                groupMessage.set(index,s.replace("\"" + replyTo,"\"" + repliedContent.substring(0,Math.min(repliedContent.length(),15)) + "..."));
            }
            if (s.contains("Forwarded From")) {
                int forwarded = Integer.parseInt(s.substring(s.indexOf("'")+1,s.lastIndexOf("'")));
                String userForwardedFrom = SQL.getUserNameByID(forwarded);
                groupMessage.set(index,s.replace("'"+forwarded+"'",userForwardedFrom));
            }
            if (s.contains("@")) {
                int said =  Integer.parseInt(s.substring(s.indexOf("@")+1,s.lastIndexOf("@")));
                String saidName = SQL.getUserNameByID(said);
                groupMessage.set(index,groupMessage.get(index).replace("@"+said+"@",saidName));
            }
        }
        return groupMessage;
    }

    public static String getGroupContentByID (int groupMessageID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT Content FROM groupmessage WHERE ID = '" + groupMessageID + "';");
        if (!resultSet.next()) return "Message Deleted";
        return resultSet.getString("Content");
    }

    public static boolean canDeleteMessageInGroup (int myID,int groupID,int messageID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT SenderID FROM groupmessage WHERE ID = '" + messageID + "' AND GroupID = '" + groupID + "';");
        if (!resultSet.next()) return false;
        return resultSet.getInt("SenderID") == myID;
    }

    public static boolean canEditMessageInGroup (int myID,int groupID,int messageID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT SenderID,ForwardFrom FROM groupmessage WHERE ID = '" + messageID + "' AND GroupID = '" + groupID + "';");
        if (!resultSet.next()) return false;
        return resultSet.getInt("SenderID") == myID && resultSet.getInt("ForwardFrom") == 0;
    }

    public static void insertNewMessageIntoGroup (int groupID,int senderID,String content,String time) throws SQLException {
        statement.execute("INSERT INTO groupmessage (GroupID, SenderID, Content, RepliedTo, ForwardFrom, Time) VALUES ('" + groupID + "','" + senderID + "','" + content +"','0','0','" + time +"');");
    }

    public static void editMessageInGroup (int messageID,String newContent) throws SQLException {
        statement.execute("UPDATE groupmessage SET Content = '" + newContent + "' WHERE ID = '" + messageID + "';");
    }

    public static void deleteMessageInDirectMessage (int messageID) throws SQLException {
        statement.execute("DELETE FROM directmessage WHERE ID = '" + messageID + "';");
    }

    public static void deleteMessageInGroup (int messageID) throws SQLException {
        statement.execute("DELETE FROM groupmessage WHERE ID = '" + messageID + "';");
    }

    public static short checkBlock (int myID,int personID) throws SQLException {
        String between = Math.min(myID,personID) + "&" + Math.max(myID,personID);
        String sql = "SELECT blocker FROM blockes WHERE blockChat = '" + between + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        if (!resultSet.next()) return -1; //-1 means no one blocks another
        if (resultSet.getInt("blocker") == myID) return 1; //1 mean I block that user
        if (resultSet.next()) return 1;
        else return 0; //0 means that user blocks me
    }

    public static void blockUser (int myID,int personID) throws SQLException {
        String between = Math.min(myID,personID) + "&" + Math.max(myID,personID);
        statement.execute("INSERT INTO blockes (blockChat, blocker) VALUES ('" + between + "','" + myID +  "');");
    }

    public static void unBlockUser (int myID,int personID) throws SQLException {
        String between = Math.min(myID,personID) + "&" + Math.max(myID,personID);
        statement.execute("DELETE FROM blockes WHERE blockChat = '" + between + "' AND blocker = '" + myID + "';");
    }

    public static boolean isBanInGroup (int userID,int groupID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT ID FROM `groups` WHERE ID = '" + groupID + "' AND BanMembers LIKE '%&" + userID + "&%';");
        return resultSet.next();
    }

    public static void replyMessageInDirectMessage (int senderID,int receiverID,String content,int repliedTo,String time) throws SQLException {
        String between = Math.min(senderID,receiverID) + "&" + Math.max(senderID,receiverID);
        String sql = "INSERT INTO directmessage (betweenIDs, SenderID, ReceiverID, Content,RepliedTo,ForwardFrom,Time) VALUES ('" + between + "','" + senderID + "','" + receiverID + "','" + content + "','" + repliedTo +"','0','" + time +"');";
        statement.execute(sql);
    }

    public static void replyMessageInGroup (int groupID,int senderID,String content,int repliedTo,String time) throws SQLException {
        statement.execute("INSERT INTO groupmessage (GroupID, SenderID, Content, RepliedTo, ForwardFrom, Time) VALUES ('" + groupID + "','" + senderID + "','" + content + "','" + repliedTo + "','0','" + time +"');");
    }

    public static void forwardMessageInDirectMessage (int senderID,int receiverID,String content,int ForwardFrom,String time) throws SQLException {
        String between = Math.min(senderID,receiverID) + "&" + Math.max(senderID,receiverID);
        String sql = "INSERT INTO directmessage (betweenIDs, SenderID, ReceiverID, Content,ForwardFrom,RepliedTo,Time) VALUES ('" + between + "','" + senderID + "','" + receiverID + "','" + content + "','" + ForwardFrom +"','0','" + time + "');";
        statement.execute(sql);
    }

    public static int getForwardedIDByDirectMessageID (int messageID) throws SQLException {
        String sql = "SELECT ForwardFrom FROM directmessage WHERE ID = '" + messageID + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getInt("ForwardFrom");
    }

    public static int getSenderIDFromDirectMessageID (int messageID) throws SQLException {
        String sql = "SELECT SenderID FROM directmessage WHERE ID = '" + messageID + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getInt("SenderID");
    }

    public static void forwardMessageInGroup (int groupID,int senderID,String content,int forwardFrom,String time) throws SQLException {
        statement.execute("INSERT INTO groupmessage (GroupID, SenderID, Content, RepliedTo, ForwardFrom, Time) VALUES ('" + groupID + "','" + senderID + "','" + content + "','0','" + forwardFrom + "','" + time +"');");
    }

    public static int getForwardedIDFromGroupMessage (int messageID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT ForwardFrom FROM groupmessage WHERE ID = '" + messageID + "';");
        resultSet.next();
        return resultSet.getInt("ForwardFrom");
    }

    public static int getSenderIDFromGroupMessage (int messageID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT SenderID FROM groupmessage WHERE ID = '" + messageID + "';");
        resultSet.next();
        return resultSet.getInt("SenderID");
    }

    public static ArrayList<Integer> getGroupMembersID (int groupID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT GroupMembers FROM `groups` WHERE ID = '" + groupID + "';");
        resultSet.next();
        String[] members = resultSet.getString("GroupMembers").split("&");
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 1;i<members.length;i++) {
            arrayList.add(Integer.parseInt(members[i]));
        }
        return arrayList;
    }

    public static void setProfileForGroup (int groupID,String Image) throws SQLException {
        statement.execute("UPDATE `groups` SET Image = '" + Image + "' WHERE ID = '" + groupID + "';");
    }

    public static boolean isAdmin (int myID,int groupID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT ID FROM `groups` WHERE ID = '" + groupID + "' AND GroupAdmin = '" + myID + "';");
        return resultSet.next();
    }

    public static void addMemberToGroup (int groupID,int memberID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT GroupMembers FROM `groups` WHERE ID = '" + groupID + "';");
        resultSet.next();
        String members = resultSet.getString("GroupMembers") + memberID + "&";
        statement.execute("UPDATE `groups` SET GroupMembers = '" + members + "' WHERE ID = '" + groupID + "';");
    }

    public static boolean isMemberOfGroup (int userID,int groupID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT ID FROM `groups` WHERE ID = '" + groupID + "' AND GroupMembers LIKE '%&" + userID + "&%';");
        return resultSet.next();
    }

    public static void removeMemberFromGroup (int groupID,int memberID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT GroupMembers FROM `groups` WHERE ID = '" + groupID + "';");
        resultSet.next();
        String members = resultSet.getString("GroupMembers").replace("&"+memberID+"&","&");
        statement.execute("UPDATE `groups` SET GroupMembers = '" + members + "' WHERE ID = '" + groupID + "';");
    }

    public static void banMember (int groupID,int memberID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT BanMembers FROM `groups` WHERE ID = '" + groupID + "';");
        resultSet.next();
        String members = resultSet.getString("BanMembers") + memberID + "&";
        statement.execute("UPDATE `groups` SET BanMembers = '" + members + "' WHERE ID = '" + groupID + "';");
    }

    public static void unbanMemberFromGroup (int groupID,int memberID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT BanMembers FROM `groups` WHERE ID = '" + groupID + "';");
        resultSet.next();
        String members = resultSet.getString("BanMembers").replace("&"+memberID+"&","&");
        statement.execute("UPDATE `groups` SET BanMembers = '" + members + "' WHERE ID = '" + groupID + "';");
    }

    public static void createNewGroup (int adminID,String groupName) throws SQLException {
        String x = "&" + adminID + "&";
        statement.execute("INSERT INTO `groups` (GroupName, GroupAdmin, GroupMembers, BanMembers) VALUES ('" + groupName + "','" + adminID + "','" + x + "','&');" );
    }

    public static boolean followsMe (int myID,int personID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT FollowerID FROM relations WHERE FollowerID = " + personID + " AND FollowingID = " + myID + ";");
        return resultSet.next();
    }

    public static void seenMessageInDirectMessage (int myID,int personID) throws SQLException {
        String between = Math.min(myID,personID) + "&" + Math.max(myID,personID);
        statement.execute("UPDATE directmessage SET Seen = '1' WHERE betweenIDs = '" + between + "' AND SenderID = '" + personID + "';");
    }

    public static int getUnSeenCount (int myID,int personID) throws SQLException {
        String between = Math.min(myID,personID) + "&" + Math.max(myID,personID);
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS total FROM directmessage WHERE betweenIDs = '" + between + "' AND SenderID = '" + personID + "' AND Seen = '0';");
        if (!resultSet.next()) return 0;
        return  resultSet.getInt("total");
    }

    public static int getUnSeenCountInGroup (int groupID,int myID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS total FROM groupmessage WHERE GroupID = '" + groupID + "' AND SenderID !='" + myID +"' AND Seen NOT LIKE '%&" +  myID + "&%';");
        if (!resultSet.next()) return 0;
        return  resultSet.getInt("total");
    }

    public static void seenMessageInGroup (int groupID,int myID) throws SQLException {
        ArrayList<Integer> messages = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT ID FROM groupmessage WHERE GroupID = '" + groupID + "';");
        while (resultSet.next())
            messages.add(resultSet.getInt("ID"));
        for (Integer integer : messages) {
            resultSet = statement.executeQuery("SELECT Seen FROM groupmessage WHERE ID = '" + integer + "';");
            resultSet.next();
            String seen = resultSet.getString("Seen") + myID + "&";
            statement.execute("UPDATE groupmessage SET Seen = '" + seen + "' WHERE ID = '" + integer + "';");
        }
    }

    public static ArrayList<Integer> getDirectMessageBefore (int myID) throws SQLException {
        ArrayList<Integer> chatBeforeID = new ArrayList<>();
        String sql = "SELECT SenderID FROM directmessage WHERE ReceiverID = " + myID + ";";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            if (!chatBeforeID.contains(resultSet.getInt("SenderID")))
                chatBeforeID.add(resultSet.getInt("SenderID"));
        }
        sql = "SELECT ReceiverID FROM directmessage WHERE SenderID = " + myID + ";";
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            if (!chatBeforeID.contains(resultSet.getInt("ReceiverID"))) {
                chatBeforeID.add(resultSet.getInt("ReceiverID"));
            }
        }
        return chatBeforeID;
    }

}
