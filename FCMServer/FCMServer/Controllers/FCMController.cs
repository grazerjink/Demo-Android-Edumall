﻿using FCMServer.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace FCMServer.Controllers
{
    public class FCMController : ApiController
    {
        [HttpGet]
        public FCM getFCM(int id)
        {
            try
            {
                string strConnection =
                    System.Configuration.ConfigurationManager.AppSettings["strConnection"];

                SqlConnection conn = new SqlConnection(strConnection);
                conn.Open();
                string sql = "select * from FCM where Id=@id";
                SqlCommand command = new SqlCommand(sql, conn);
                command.Parameters.Add("@id", SqlDbType.NVarChar).Value = id;
                SqlDataReader reader = command.ExecuteReader();
                FCM fcm = null;
                while (reader.Read())
                {
                    fcm = new FCM();
                    fcm.Id = reader.GetInt32(0);
                    fcm.Token = reader.GetString(1);
                }
                conn.Close();
                return fcm;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpGet]
        public List<FCM> getFCMS()
        {
            try
            {
                List<FCM> dsFCM = new List<FCM>();
                string strConnection =
                    System.Configuration.ConfigurationManager.AppSettings["strConnection"];

                SqlConnection conn = new SqlConnection(strConnection);
                conn.Open();
                string sql = "select * from FCM";
                SqlCommand command = new SqlCommand(sql, conn);
                SqlDataReader reader = command.ExecuteReader();
                FCM fcm = null;
                while (reader.Read())
                {
                    fcm = new FCM();
                    fcm.Id = reader.GetInt32(0);
                    fcm.Token = reader.GetString(1);
                    dsFCM.Add(fcm);
                }
                conn.Close();
                return dsFCM;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpGet]
        public FCM getFCM(string token)
        {
            try
            {
                string strConnection =
                    System.Configuration.ConfigurationManager.AppSettings["strConnection"];

                SqlConnection conn = new SqlConnection(strConnection);
                conn.Open();
                string sql = "select * from FCM where token=@token";
                SqlCommand command = new SqlCommand(sql, conn);
                command.Parameters.Add("@token", SqlDbType.NVarChar).Value = token;
                SqlDataReader reader = command.ExecuteReader();
                FCM fcm = null;
                while (reader.Read())
                {
                    fcm = new FCM();
                    fcm.Id = reader.GetInt32(0);
                    fcm.Token = reader.GetString(1);
                }
                conn.Close();
                return fcm;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        [HttpPost]
        public bool saveToken(string token)
        {
            try
            {
                if (getFCM(token) != null) return false;
                string strConnection = 
                    System.Configuration.ConfigurationManager.AppSettings["strConnection"];

                SqlConnection conn = new SqlConnection(strConnection);
                conn.Open();
                string sql = "insert into FCM(token) values(@token)";
                SqlCommand command = new SqlCommand(sql, conn);
                command.Parameters.Add("@token", SqlDbType.NVarChar).Value = token;
                int kq = command.ExecuteNonQuery();
                conn.Close();
                return kq > 0;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
    }
}
