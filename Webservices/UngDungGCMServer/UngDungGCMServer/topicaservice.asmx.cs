using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace UngDungGCMServer
{
    /// <summary>
    /// Summary description for topicaservice
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class topicaservice : System.Web.Services.WebService
    {

        [WebMethod]
        public string HelloWorld()
        {
            return "Hello World";
        }

        [WebMethod]
        public bool luuGCMDeviceID(string gcmDecviceId)
        {
            try
            {
                TopicaDatabaseDataContext context = new TopicaDatabaseDataContext();
                GCM gcm = new GCM();
                gcm.GCMDeviceID = gcmDecviceId;
                context.GCMs.InsertOnSubmit(gcm);
                context.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
    }
}
