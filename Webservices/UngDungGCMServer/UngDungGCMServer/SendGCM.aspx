<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="SendGCM.aspx.cs" Inherits="UngDungGCMServer.SendGCM" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <asp:Label ID="Label1" runat="server" Text="Nhập nội dung thông báo :"></asp:Label>
    
    </div>
        <p>
            <asp:TextBox ID="txtNoiDung" runat="server" Height="91px" TextMode="MultiLine" Width="355px"></asp:TextBox>
        </p>
        <asp:Button ID="btnGuiThongBao" runat="server" Text="Gửi thông báo" OnClick="btnGuiThongBao_Click" />
        <p>
            <asp:Label ID="Label2" runat="server" Text="Kết quả thực hiện Push Message (GCM)"></asp:Label>
        </p>
        <p>
            <asp:TextBox ID="txtKetQua" runat="server" Height="96px" Width="351px"></asp:TextBox>
        </p>
    </form>
</body>
</html>
