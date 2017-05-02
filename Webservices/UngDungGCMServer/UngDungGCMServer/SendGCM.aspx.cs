using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace UngDungGCMServer
{
    public partial class SendGCM : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnGuiThongBao_Click(object sender, EventArgs e)
        {
            try
            {
                TopicaDatabaseDataContext db = new TopicaDatabaseDataContext();
                String appID = "";
                String[] arrRegId = db.GCMs.Select(x => x.GCMDeviceID).ToArray();
                String SENDER_ID = "";
                string value = txtNoiDung.Text;
                WebRequest tRequest;
                //Thiet lap GCM Send
                tRequest = WebRequest.Create("android.googleapis.com/gcm/send");
                tRequest.Method = "POST";
                tRequest.UseDefaultCredentials = true;

                tRequest.PreAuthenticate = true;
                tRequest.Credentials = CredentialCache.DefaultNetworkCredentials;

                //Dinh dang JSON
                tRequest.ContentType = "application/json";
                tRequest.Headers.Add(string.Format("Authorization: key={0}", appID));
                tRequest.Headers.Add(string.Format("Sender: id{0}", SENDER_ID));

                string RegArr = string.Empty;
                RegArr = string.Join("\",\"", arrRegId);

                string postData = "{ \"registration_ids\": [ \"" + RegArr + "\" ],\"data\": {\"message\": \"" + value + "\",\"collapse_key\":\"" + value + "\"}}";

                Console.WriteLine(postData);
                Byte[] byteArray = Encoding.UTF8.GetBytes(postData);
                tRequest.ContentLength = byteArray.Length;

                Stream dataStream = tRequest.GetRequestStream();

                dataStream.Write(byteArray, 0, byteArray.Length);
                dataStream.Close();

                WebResponse tRespone = tRequest.GetResponse();
                dataStream = tRespone.GetResponseStream();

                StreamReader tReader = new StreamReader(dataStream);

                String sResponseFromServer = tReader.ReadToEnd();

                txtKetQua.Text = sResponseFromServer;

                tReader.Close();
                dataStream.Close();
                tRespone.Close();
            }
            catch (Exception ex)
            {
                txtKetQua.Text = ex.Message;
            }








        }
    }
}