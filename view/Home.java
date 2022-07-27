package view;

import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
public class Home
{
    protected static String myUserName;
    protected static int myID;
    protected static boolean businessAccount;
    static Scanner scanner = new Scanner(System.in);
    public static void home () throws SQLException
    {
        int x;
        System.out.println("***************************** home page *****************************");
        System.out.println("you logged in as " + myUserName + "!");
        while (true)
        {
            System.out.println("1.Create new post");
            System.out.println("2.See other's posts");
            System.out.println("3.See your own posts");
            System.out.println("4.Search a user");
            System.out.println("5.Your chats");
            System.out.println("6.Your groups");
            System.out.println("7.Your followers");
            System.out.println("8.Your followings");
            System.out.println("9.Suggested users");
            if(businessAccount)
            {
                System.out.println("10.Profile views");
                System.out.println("11.Logout");
            }
            else
            {
                System.out.println("10.Logout");
            }
            x = scanner.nextInt();
            if (x==1)
            {
                System.out.println("Enter the content...");
                scanner.nextLine();
                String postContent=scanner.nextLine();
                User.createNewPost(myID,postContent,businessAccount);
                System.out.println("You created a new post successfully!");
            }
            if (x==2)
            {
                ArrayList<Integer> postIDs=User.getFollowingsPostIDByID(myID);
                postIDs.addAll(User.getSuggestedPostIDs(myID));
                int i;
                for(i=postIDs.size()-1;i>=0;)
                {
                    System.out.println(User.getUserNameByID(User.getUserIDByContentID(postIDs.get(i)))+" posted : "+User.getContentByContentID(postIDs.get(i)));
                    if(User.getBoolADByID(User.getUserIDByContentID(postIDs.get(i))))
                    {
                        System.out.println("AD Post!");
                    }
                    if(User.getBoolADByID(User.getUserIDByContentID(postIDs.get(i))))
                    {
                        User.postView(postIDs.get(i));
                    }
                    boolean postLiked = User.liked(myID,postIDs.get(i));
                    if(postLiked)
                    {
                        System.out.println("1.Unlike");
                    }
                    else
                    {
                        System.out.println("1.Like");
                    }
                    System.out.println("2.Comment");
                    System.out.println("3.See likes");
                    System.out.println("4.See comments");
                    System.out.println("5.Next post");
                    System.out.println("6.Previous post");
                    System.out.println("7.Exit");
                    int y=scanner.nextInt();
                    int j=i;
                    if(y==1 && postLiked)
                    {
                        User.unlike(myID,postIDs.get(i));
                    }
                    if(y==1 && !postLiked)
                    {
                        String Date;
                        if(User.getBoolADByID(User.getUserIDByContentID(postIDs.get(i))))
                        {
                            Date = java.time.LocalDate.now().toString();
                        }
                        else
                        {
                            Date = "0";
                        }
                        User.like(myID,postIDs.get(i),Date);
                    }
                    if(y==2)
                    {
                        System.out.print("Enter your comment : ");
                        scanner.nextLine();
                        String comment=scanner.nextLine();
                        User.comment(myID,postIDs.get(i),comment);
                        System.out.println("You commented successfully!");
                    }
                    if(y==3)
                    {
                        ArrayList<String> userNames = User.userNamesOfLikes(postIDs.get(i));
                        if(userNames.size()==0)
                        {
                            System.out.println("No likes!");
                        }
                        else
                        {
                            System.out.println("Likes :");
                            int k;
                            for (k = 0; k < userNames.size(); k++)
                            {
                                System.out.println(userNames.get(k));
                            }
                        }
                    }
                    if(y==4)
                    {
                        ArrayList<Integer> commentsIDs = User.getCommentsIDOfPost(postIDs.get(i));
                        ArrayList<Integer> UserIDs = User.getUserIDsForComments(postIDs.get(i));
                        if(commentsIDs.size()==0)
                        {
                            System.out.println("No comments!");
                        }
                        else
                        {
                            int k;
                            for (k = commentsIDs.size() - 1; k >= 0; )
                            {
                                System.out.println(User.getUserNameByID(UserIDs.get(k))+" commented : "+User.getContentByContentID(commentsIDs.get(k)));
                                boolean commentLiked = User.liked(myID, commentsIDs.get(k));
                                if (commentLiked)
                                {
                                    System.out.println("1.Unlike");
                                }
                                else
                                {
                                    System.out.println("1.Like");
                                }
                                System.out.println("2.Reply");
                                System.out.println("3.See likes");
                                System.out.println("4.View replies");
                                System.out.println("5.Next comment");
                                System.out.println("6.Previous comment");
                                System.out.println("7.Exit");
                                int z = scanner.nextInt();
                                if (z == 1 && commentLiked)
                                {
                                    User.unlike(myID, commentsIDs.get(k));
                                }
                                if (z == 1 && !commentLiked)
                                {
                                    User.like(myID, commentsIDs.get(k),"0");
                                }
                                if (z == 2)
                                {
                                    System.out.print("Enter your reply : ");
                                    scanner.nextLine();
                                    String reply = scanner.nextLine();
                                    User.reply(myID, commentsIDs.get(k), reply);
                                    System.out.println("You replied successfully!");
                                }
                                if (z == 3)
                                {
                                    ArrayList<String> userNames = User.userNamesOfLikes(commentsIDs.get(k));
                                    if (userNames.size() == 0)
                                    {
                                        System.out.println("No likes!");
                                    }
                                    else
                                    {
                                        System.out.println("Likes :");
                                        int h;
                                        for (h = 0; h < userNames.size(); h++)
                                        {
                                            System.out.println(userNames.get(h));
                                        }
                                    }
                                }
                                if (z == 4)
                                {
                                    ArrayList<String> replies = User.getRepliesOfComment(commentsIDs.get(k));
                                    int h;
                                    if(replies.size()==0)
                                    {
                                        System.out.println("No replies!");
                                    }
                                    else
                                    {
                                        for (h = replies.size() - 1; h >= 0; h--)
                                        {
                                            System.out.println(replies.get(h));
                                        }
                                    }
                                }
                                if (z==5 && k==0)
                                {
                                    System.out.println("There is no more comments!");
                                }
                                if (z==5 && k!=0)
                                {
                                    k--;
                                }
                                if(z==6 && k==commentsIDs.size()-1)
                                {
                                    System.out.println("There is no previous comments!");
                                }
                                if (z==6 && k!=commentsIDs.size()-1)
                                {
                                    k++;
                                }
                                if(z==7)
                                {
                                    break;
                                }
                            }
                        }
                    }
                    if(y==5 && j!=0)
                    {
                        i--;
                    }
                    if(y==5 && j==0)
                    {
                        System.out.println("There is no more posts!");
                    }
                    if(y==6 && j!= postIDs.size()-1)
                    {
                        i++;
                    }
                    if(y==6 && j== postIDs.size()-1)
                    {
                        System.out.println("There is no previous post!");
                    }
                    if(y==7)
                    {
                        break;
                    }
                }
            }
            if (x==3)
            {
                ArrayList<Integer> postsIDs=User.getPostsIDByUserID(myID);
                int i;
                for(i= postsIDs.size()-1;i>=0;)
                {
                    System.out.println("You posted : "+User.getContentByContentID(postsIDs.get(i)));
                    System.out.println("1.Next post");
                    System.out.println("2.Previous post");
                    System.out.println("3.Remove the post");
                    if(businessAccount)
                    {
                        System.out.println("4.See stats");
                        System.out.println("5.Exit");
                    }
                    else
                    {
                        System.out.println("4.Exit");
                    }
                    int y=scanner.nextInt();
                    int j=i;
                    if(y==1 && j!= 0)
                    {
                        i--;
                    }
                    if(y==1 && j==0)
                    {
                        System.out.println("There is no more posts!");
                    }
                    if(y==2 && j!= postsIDs.size()-1)
                    {
                        i++;
                    }
                    if(y==2 && j== postsIDs.size()-1)
                    {
                        System.out.println("There is no previous post!");
                    }
                    if(y==3)
                    {
                        User.removePostByID(postsIDs.get(i));
                        postsIDs=User.getPostsIDByUserID(myID);
                        System.out.println("You removed the post successfully!");
                        i= postsIDs.size()-1;
                    }
                    if(y==4 && businessAccount)
                    {
                        System.out.println("1.Views");
                        System.out.println("2.Likes");
                        System.out.println("3.Exit");
                        int z = scanner.nextInt();
                        if(z==1)
                        {
                            ArrayList<String> views = User.getPostViews(postsIDs.get(i));
                            int k;
                            for(k= views.size()-1;k>=0;k--)
                            {
                                System.out.println(views.get(k));
                            }
                        }
                        if(z==2)
                        {
                            ArrayList<String> likes = User.getLikesStats(postsIDs.get(i));
                            int k;
                            if(likes.size()==0)
                            {
                                System.out.println("No likes!");
                            }
                            else
                            {
                                for (k = likes.size()-1 ;k>=0; k--)
                                {
                                    System.out.println(likes.get(k));
                                }
                            }
                        }
                    }
                    if(y==4 && !businessAccount)
                    {
                        break;
                    }
                    if(y==5 && businessAccount)
                    {
                        break;
                    }
                }
                if(postsIDs.size()==0)
                {
                    System.out.println("No posts!");
                }
            }
            if (x==4)
            {
                System.out.println("Enter the user name...");
                String searchedUserName = scanner.next();
                if (User.getIDByUserName(searchedUserName) == -1)
                {
                    System.out.println("No result!");
                }
                else
                {
                    int searchedUserID = User.getIDByUserName(searchedUserName);
                    boolean ADAccount = User.getBoolADByID(searchedUserID);
                    if(ADAccount)
                    {
                        User.profileView(searchedUserID,java.time.LocalDate.now().toString());
                    }
                    System.out.println("1.See posts");
                    boolean followCheck=false;
                    ArrayList<Integer> followingsIdInSearch=User.getFollowingsIDByID(myID);
                    int i;
                    for(i=0;i< followingsIdInSearch.size();i++)
                    {
                        if(followingsIdInSearch.get(i).equals(searchedUserID))
                        {
                            followCheck=true;
                            break;
                        }
                    }
                    if(!followCheck)
                    {
                        System.out.println("2.Follow");
                    }
                    else
                    {
                        System.out.println("2.Unfollow");
                    }
                    int y = scanner.nextInt();
                    if (y == 1)
                    {
                        ArrayList<Integer> searchedUserPostsIDs=User.getPostsIDByUserID(searchedUserID);
                        for(i=searchedUserPostsIDs.size()-1;i>=0;)
                        {
                            System.out.println(searchedUserName+" posted : "+User.getContentByContentID(searchedUserPostsIDs.get(i)));
                            boolean liked = User.liked(myID,searchedUserPostsIDs.get(i));
                            if (ADAccount)
                            {
                                User.postView(searchedUserPostsIDs.get(i));
                            }
                            if(liked)
                            {
                                System.out.println("1.Unlike");
                            }
                            else
                            {
                                System.out.println("1.Like");
                            }
                            System.out.println("2.Comment");
                            System.out.println("3.See likes");
                            System.out.println("4.See comments");
                            System.out.println("5.Next post");
                            System.out.println("6.Previous Post");
                            System.out.println("7.Exit");
                            int z=scanner.nextInt();
                            int j=i;
                            if(z==1 && liked)
                            {
                                User.unlike(myID,searchedUserPostsIDs.get(i));
                            }
                            if(z==1 && !liked)
                            {
                                String Date;
                                if(User.getBoolADByID(searchedUserID))
                                {
                                    Date = java.time.LocalDate.now().toString();
                                }
                                else
                                {
                                    Date = "0";
                                }
                                User.like(myID,searchedUserPostsIDs.get(i),Date);
                            }
                            if(z==3)
                            {
                                ArrayList<String> userNames=User.userNamesOfLikes(searchedUserPostsIDs.get(i));
                                if(userNames.size()==0)
                                {
                                    System.out.println("No likes!");
                                }
                                else
                                {
                                    System.out.println("Likes :");
                                    int k;
                                    for(k=0;k< userNames.size();k++)
                                    {
                                        System.out.println(userNames.get(k));
                                    }
                                }
                            }
                            if(z==5 && j!=0)
                            {
                                i--;
                            }
                            if(z==5 && j==0)
                            {
                                System.out.println("There is no more posts!");
                            }
                            if(z==6 && i!=searchedUserPostsIDs.size()-1)
                            {
                                i++;
                            }
                            if(z==6 && i==searchedUserPostsIDs.size()-1)
                            {
                                System.out.println("There is no previous post!");
                            }
                            if(z==7)
                            {
                                break;
                            }
                        }
                        if(searchedUserPostsIDs.size()==0)
                        {
                            System.out.println("No posts!");
                        }
                    }
                    if (y == 2 && !followCheck)
                    {
                        User.follow(myID, searchedUserID);
                        System.out.println("You followed " + searchedUserName + " successfully!");
                    }
                    if(y == 2 && followCheck)
                    {
                        User.unfollow(myID,searchedUserID);
                        System.out.println("You unfollowed "+searchedUserName +" successfully!");
                    }
                }
            }

            if (x == 5) {
                scanner.nextLine();
                while (true) {
                    System.out.println("Your Chats :");
                    ArrayList<Integer> myGroupID =  User.getAllMyGroupName(myID);
                    ArrayList<String> myGroupName = new ArrayList<>();
                    for (Integer integer : myGroupID) {
                        String name = User.getGroupNameByID(integer);
                        myGroupName.add(name);
                    }
                    for (String chatBefore : User.getDirectMessageBefore(myID)) {
                        System.out.println(chatBefore);
                    }
                    System.out.println("enter your chat or enter new user name or enter exit");
                    String y = scanner.nextLine();
                    if (y.equals("exit")) break;
                    int IDToChat = User.getIDByUserName(y);
                    if (IDToChat == -1) {System.out.println("User Not Found"); continue;}
                    for (String s : User.getChatContent(myID, IDToChat)) {
                        System.out.println(s);
                    }
                    while (true) {
                        int blockCondition = User.checkBlock(myID,IDToChat);
                        if (blockCondition == -1) System.out.println("1.send message");
                        else System.out.println("you cant send message to this user");
                        System.out.println("2.edit message\n3.delete message");
                        if (blockCondition == -1) System.out.println("4.reply message");
                        else System.out.println("you cant reply message to this user");
                        System.out.println("5.forward message\n6.search message");
                        if (blockCondition == 1) System.out.println("7.unblock this user\n8.exit");
                        else System.out.println("7.block this user\n8.exit");

                        int z = scanner.nextInt();
                        scanner.nextLine();
                        if (z == 1 && blockCondition == -1) {
                            System.out.println("enter your message");
                            y = scanner.nextLine();
                            if (!User.insertNewDirectMessage(myID, IDToChat, y,java.time.LocalTime.now().toString().substring(0,8))) System.out.println("something went wrong");
                            for (String s : User.getChatContent(myID, IDToChat)) {
                                System.out.println(s);
                            }
                        }

                        if (z == 2) {
                            System.out.println("enter ID of message that you want to edit");
                            int messageID = scanner.nextInt();
                            scanner.nextLine();
                            if (User.hasAccessToEditMessage(myID,messageID)) {
                                System.out.println("enter your edited message");
                                User.editMessage(messageID,scanner.nextLine());
                                for (String s : User.getChatContent(myID, IDToChat)) {
                                    System.out.println(s);
                                }
                            }
                            else System.out.println("you don't have access to edit this message");
                        }
                        if (z == 3) {
                            System.out.println("enter ID of message that you want to delete");
                            int messageID = scanner.nextInt();
                            scanner.nextLine();
                            if (User.hasAccessToDeleteMessage(myID,messageID)) {
                                User.deleteMessage(messageID);
                                for (String s : User.getChatContent(myID, IDToChat)) {
                                    System.out.println(s);
                                }
                            }
                            else System.out.println("you don't have access to delete this message");
                        }
                        if (z == 4 && blockCondition == -1) {
                            System.out.println("enter ID of message that you want to reply");
                            int messageID = scanner.nextInt();
                            scanner.nextLine();
                            if (User.canReplyForward(myID,IDToChat,messageID)) {
                                System.out.println("enter your reply");
                                User.replyMessage(myID,IDToChat,scanner.nextLine(),messageID,java.time.LocalTime.now().toString().substring(0,8));
                                for (String s : User.getChatContent(myID, IDToChat)) {
                                    System.out.println(s);
                                }
                            }
                            else System.out.println("you can't reply this message");
                        }
                        if (z == 5) {
                            System.out.println("enter ID of message that you want to Forward");
                            int messageID = scanner.nextInt();
                            scanner.nextLine();
                            if (User.canReplyForward(myID,IDToChat,messageID)) {
                                System.out.println("enter name of user or group that you want to forward this message");
                                String userNameToForward = scanner.nextLine();
                                int origin = User.getForwardedIDByDirectMessageID(messageID);
                                if (origin == 0) origin = User.getSenderIDFromDirectMessageID(messageID);
                                boolean sentToUser = true;
                                int IDToForward = User.getIDByUserName(userNameToForward);
                                if (IDToForward == -1) {
                                    if (myGroupName.contains(userNameToForward)) {
                                        IDToForward = myGroupID.get(myGroupName.indexOf(userNameToForward));
                                        sentToUser = false;
                                    }
                                    else {
                                        System.out.println("User or Group Not Found"); continue;
                                    }
                                }

                                if (sentToUser) {
                                    int blockForwardCondition = User.checkBlock(myID,IDToForward);
                                    if (blockForwardCondition == -1) {
                                        User.forwardMessage(myID, IDToForward, User.getContentDirectMessageByID(messageID), origin, java.time.LocalTime.now().toString().substring(0, 8));
                                        System.out.println("FORWARDED!");
                                    }
                                    else
                                        System.out.println("you can't forward message to this user");
                                }
                                else {
                                    boolean myBanCondition = User.isBanInGroup(myID,IDToForward);
                                    if (!myBanCondition) {
                                        User.forwardMessageInGroup(IDToForward, myID, User.getGroupContentByID(messageID), origin, java.time.LocalTime.now().toString().substring(0, 8));
                                        System.out.println("FORWARDED!");
                                    }
                                    else
                                        System.out.println("you cant forward message to this group");
                                }
                            }
                            else
                                System.out.println("you can't forward this message");
                        }
                        if (z == 6) {
                            System.out.println("enter your message that you want to search");
                            ArrayList<String> searchResult = User.searchInDirectMessage(myID,IDToChat,scanner.nextLine());
                            if (searchResult.isEmpty()) System.out.println("No results!");
                            else {
                                for (String s : searchResult) {
                                    System.out.println(s);
                                }
                                int search = scanner.nextInt();
                                scanner.nextLine();
                                boolean valid = false;
                                for (String s : searchResult) {
                                    if (s.contains("ID = " + search)) {
                                        int siad = User.getSenderIDFromDirectMessageID(search);
                                        String siadName = User.getUserNameByID(siad);
                                        if (siad == myID) siadName = "YOU";
                                        System.out.println(siadName + " : " + User.getContentDirectMessageByID(search));
                                        valid = true;
                                    }
                                }
                                if (!valid) System.out.println("Invalid ID");
                            }
                        }
                        if (z == 7) {
                            if (blockCondition == 1)
                                User.unBlockUser(myID,IDToChat);
                            else
                                User.blockUser(myID,IDToChat);
                        }
                        if (z == 8) break;
                    }
                }
            }

            if (x == 6) {
                scanner.nextLine();
                while (true) {
                    System.out.println("Your Groups : ");
                    ArrayList<Integer> myGroupID =  User.getAllMyGroupName(myID);
                    ArrayList<String> myGroupName = new ArrayList<>();
                    for (Integer integer : myGroupID) {
                        String name = User.getGroupNameByID(integer);
                        System.out.print(name);
                        System.out.println( " (" + User.getLastMessageInChat(myID,integer) + ")");
                        myGroupName.add(name);
                    }
                    System.out.println("enter group name or enter create to create new group or enter exit");
                    String input = scanner.nextLine();
                    if (input.equals("exit")) break;
                    if (input.equals("create")) {
                        System.out.println("enter group name");
                        User.createNewGroup(myID,scanner.nextLine());
                        continue;
                    }
                    int groupID;
                    boolean isAdmin;
                    if (myGroupName.contains(input)) {
                        groupID = myGroupID.get(myGroupName.indexOf(input));
                        isAdmin = User.isAdmin(myID,groupID);
                    }
                    else  {System.out.println("Group Not Found"); continue;}

                    for (String s : User.getGroupMessage(myID, groupID)) {
                        System.out.println(s);
                    }
                    while (true) {
                        boolean myBanCondition = User.isBanInGroup(myID,groupID);
                        if (myBanCondition) System.out.println("you can't send message in this group");
                        else System.out.println("1.Send Message");
                        System.out.println("2.edit Message\n3.delete message");
                        if (myBanCondition) System.out.println("you can't reply message in this group");
                        else System.out.println("4.reply message");
                        System.out.println("5.forward message\n6.search message\n7.see members\n8.leave group");
                        if (isAdmin) System.out.println("9.add member\n10.remove member\n11.see ban members\n12.ban member\n13.unban member\n14.exit");
                        else System.out.println("9.exit");
                        int z = scanner.nextInt();
                        scanner.nextLine();
                        if (z == 1 && !myBanCondition) {
                            System.out.println("enter your message");
                            User.insertNewMessageIntoGroup(groupID,myID,scanner.nextLine(),java.time.LocalTime.now().toString().substring(0,8));
                            for (String s : User.getGroupMessage(myID, groupID)) {
                                System.out.println(s);
                            }
                        }
                        if (z == 2) {
                            System.out.println("enter ID of message that you want to edit");
                            int messageID = Integer.parseInt(scanner.nextLine());
                            if (User.canEditMessageInGroup(myID,groupID,messageID)) {
                                System.out.println("enter your edited message");
                                User.editMessageInGroup(messageID,scanner.nextLine());
                                for (String s : User.getGroupMessage(myID, groupID)) {
                                    System.out.println(s);
                                }
                            }
                            else System.out.println("you can't edit this message");
                        }
                        if (z == 3) {
                            System.out.println("enter ID of message that you want to edit");
                            int messageID = Integer.parseInt(scanner.nextLine());
                            if (User.canDeleteMessageInGroup(myID,groupID,messageID)) {
                                User.deleteMessageInGroup(messageID);
                                for (String s : User.getGroupMessage(myID, groupID)) {
                                    System.out.println(s);
                                }
                            }
                            else System.out.println("you can't delete this message");
                        }
                        if (z == 4 && !myBanCondition) {
                            System.out.println("enter ID of message that you want to reply");
                            int messageID = scanner.nextInt();
                            scanner.nextLine();
                            if (User.canReplyForwardInGroup(groupID,messageID)) {
                                System.out.println("enter your reply");
                                User.replyMessageInGroup(groupID,myID,scanner.nextLine(),messageID,java.time.LocalTime.now().toString().substring(0,8));
                                for (String s : User.getGroupMessage(myID, groupID)) {
                                    System.out.println(s);
                                }
                            }
                            else System.out.println("you can't reply this message");
                        }
                        if (z == 5) {
                            System.out.println("enter ID of message that you want to Forward");
                            int messageID = scanner.nextInt();
                            scanner.nextLine();
                            if (User.canReplyForwardInGroup(groupID,messageID)) {
                                System.out.println("enter name of user or group that you want to forward this message");
                                String userNameToForward = scanner.nextLine();
                                int origin = User.getForwardedIDFromGroupMessage(messageID);
                                if (origin == 0) origin = User.getSenderIDFromGroupMessage(messageID);
                                boolean sentToUser = true;
                                int IDToForward = User.getIDByUserName(userNameToForward);
                                if (IDToForward == -1) {
                                    if (myGroupName.contains(userNameToForward)) {
                                        IDToForward = myGroupID.get(myGroupName.indexOf(userNameToForward));
                                        sentToUser = false;
                                    }
                                    else {
                                        System.out.println("User or Group Not Found"); continue;
                                    }
                                }
                                if (sentToUser) {
                                    int blockForwardCondition = User.checkBlock(myID,IDToForward);
                                    if (blockForwardCondition == -1) {
                                        User.forwardMessage(myID, IDToForward, User.getContentDirectMessageByID(messageID), origin, java.time.LocalTime.now().toString().substring(0, 8));
                                        System.out.println("FORWARDED!");
                                    }
                                    else
                                        System.out.println("you can't forward message to this user");
                                }
                                else {
                                    if (!myBanCondition) {
                                        User.forwardMessageInGroup(IDToForward, myID, User.getGroupContentByID(messageID), origin, java.time.LocalTime.now().toString().substring(0, 8));
                                        System.out.println("FORWARDED!");
                                    }
                                    else
                                        System.out.println("you can't forward message to this group");
                                }
                            }
                            else
                                System.out.println("you can't forward this message");
                        }
                        if (z == 6) {
                            System.out.println("enter your message that you want to search");
                            ArrayList<String> searchResult = User.searchInGroupMessage(groupID,myID,scanner.nextLine());
                            if (searchResult.isEmpty()) System.out.println("No results!");
                            else {
                                for (String s : searchResult) {
                                    System.out.println(s);
                                }
                                int search = scanner.nextInt();
                                scanner.nextLine();
                                boolean valid = false;
                                for (String s : searchResult) {
                                    if (s.contains("ID = " + search)) {
                                        int siad = User.getSenderIDFromGroupMessage(search);
                                        String siadName = User.getUserNameByID(siad);
                                        if (siad == myID) siadName = "YOU";
                                        System.out.println(siadName + " : " + User.getGroupContentByID(search));
                                        valid = true;
                                    }
                                }
                                if (!valid) System.out.println("Invalid ID");
                            }
                        }
                        if (z == 7) {
                            for (Integer integer : User.getGroupMemberID(groupID)) {
                                System.out.println(User.getUserNameByID(integer));
                            }
                        }
                        if (z == 8) {
                            System.out.println("enter yes if sure to leave this group or enter no to cancel");
                            if (scanner.nextLine().equals("yes"))
                                User.removeMemberFromGroup(groupID,myID);
                        }
                        if (z == 9 && isAdmin) {
                            System.out.println("enter your username that you want to add");
                            int userID = User.getIDByUserName(scanner.nextLine());
                            if (userID == -1)
                                System.out.println("User Not Found");
                            else
                                User.addMemberToGroup(groupID,userID);
                        }
                        if (z == 10 && isAdmin) {
                            System.out.println("members of group : ");
                            for (Integer integer : User.getGroupMemberID(groupID)) {
                                if (integer == myID) continue;
                                System.out.println(User.getUserNameByID(integer));
                            }
                            System.out.println("enter member that you want to remove");
                            int userIDToRemove = User.getIDByUserName(scanner.nextLine());
                            if (User.isMemberOfGroup(userIDToRemove,groupID))
                                User.removeMemberFromGroup(groupID,userIDToRemove);
                            else
                                System.out.println("invalid user");
                        }
                        if (z == 11 && isAdmin) {
                            System.out.println("baned user in group : ");
                            for (Integer integer : User.getBanMemberID(groupID)) {
                                System.out.println(User.getUserNameByID(integer));
                            }
                        }
                        if (z == 12 && isAdmin) {
                            System.out.println("members of group : ");
                            for (Integer integer : User.getGroupMemberID(groupID)) {
                                if (integer == myID) continue;
                                System.out.println(User.getUserNameByID(integer));
                            }
                            System.out.println("enter member that you want to ban");
                            int userIDToBan = User.getIDByUserName(scanner.nextLine());
                            if (User.isMemberOfGroup(userIDToBan,groupID))
                                User.banMember(groupID,userIDToBan);
                            else
                                System.out.println("invalid user");
                        }
                        if (z == 13 && isAdmin) {
                            System.out.println("baned user in group : ");
                            for (Integer integer : User.getBanMemberID(groupID)) {
                                System.out.println(User.getUserNameByID(integer));
                            }
                            System.out.println("enter member that you want to unban");
                            int unbanUserID = User.getIDByUserName(scanner.nextLine());
                            if (User.isMemberOfGroup(unbanUserID,groupID))
                                if (User.isBanInGroup(unbanUserID,groupID))
                                    User.unbanMemberFromGroup(groupID,unbanUserID);
                                else
                                    System.out.println("invalid user");
                            else
                                System.out.println("invalid user");
                        }
                        if (z == 8 && !isAdmin) break;
                        if (z == 14 && isAdmin) break;
                    }
                 }
            }

            if (x==7)
            {
                ArrayList<String> followersUserName=User.getFollowersUserNameByID(myID);
                int i;
                for(i=0;i< followersUserName.size();i++)
                {
                    System.out.println(followersUserName.get(i));
                }
            }
            if (x==8)
            {
                ArrayList<String> followingsID=User.getFollowingsUserNameByID(myID);
                int i;
                for(i=0;i< followingsID.size();i++)
                {
                    System.out.println(followingsID.get(i));
                }
            }
            if (x==9)
            {
                ArrayList<Integer> suggestedUsersIDs = User.getSuggestedUserIDsByUserID(myID);
                int i;
                if(suggestedUsersIDs.size()==0)
                {
                    System.out.println("No suggested user!");
                }
                else
                {
                    System.out.println("Suggested users :");
                    for (i = 0; i < suggestedUsersIDs.size(); i++)
                    {
                        System.out.println(User.getUserNameByID(suggestedUsersIDs.get(i)));
                    }
                }
            }
            if (x==10 && businessAccount)
            {
                ArrayList<String> views = User.getProfileViewsByID(myID);
                int i;
                for(i=views.size()-1;i>=0;i--)
                {
                    System.out.println(views.get(i));
                }
            }
            if (x==10 && !businessAccount)
            {
                myUserName = null;
                Manager.HomeBool = false;
                Manager.LoginBool = true;
                break;
            }
            if(x==11 && businessAccount)
            {
                myUserName = null;
                Manager.HomeBool = false;
                Manager.LoginBool = true;
                break;
            }
        }
    }
}
