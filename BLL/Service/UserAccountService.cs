using DAL.Entities;
using DAL.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BLL.Service
{
    public class UserAccountService
    {
        private UserAccountRepo _accountRepository;
        public UserAccountService()
        {
            _accountRepository = new UserAccountRepo();
        }

        // ham login
        public UserAccount? GetAccount(string email, string password)
        {
            return _accountRepository.GetAccount(email, password);
        }

    }
}
