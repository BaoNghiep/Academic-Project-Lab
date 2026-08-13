using DAL.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL.Repositories
{
    public class UserAccountRepo
    {
        private Sp25eventDbContext _db;
        public UserAccountRepo()
        {
            _db = new Sp25eventDbContext();
        }

        // ham login
        public UserAccount? GetAccount(string email, string password)
        {
            return _db.UserAccounts.FirstOrDefault(x => x.Email == email && x.Password == password);
        }
    }
}
