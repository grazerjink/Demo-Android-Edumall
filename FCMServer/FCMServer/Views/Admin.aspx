<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Admin.aspx.cs" Inherits="FCMServer.Views.Admin" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        Nhập tiêu đề muốn thông báo :</div>
        <asp:TextBox ID="txtTieuDe" runat="server" Width="303px"></asp:TextBox>
        <br />
        Nhập nội dung muốn gửi thông báo :<br />
        <asp:TextBox ID="txtNoiDung" runat="server" Height="118px" TextMode="MultiLine" Width="305px"></asp:TextBox>
        <br />
        <asp:Button ID="btnGui" runat="server" OnClick="btnGui_Click" Text="Gửi thông báo tới toàn bộ mobiles" Width="257px" />
        <br />
        Kết quả sau khi gửi thông báo :<br />
        <asp:TextBox ID="txtKetQua" runat="server" Height="87px" TextMode="MultiLine" Width="305px"></asp:TextBox>
    </form>
</body>
</html>
