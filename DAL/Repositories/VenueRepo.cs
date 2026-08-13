using DAL.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL.Repositories
{
    public class VenueRepo
    {
        private Sp25eventDbContext _db;
        public VenueRepo()
        {
            _db = new Sp25eventDbContext();
        }

        // ham lay het thong tin bang Venue
        public List<Venue> GetVenues()
        {
            return _db.Venues.ToList();
        }

    }
}
